package ru.sumenkov.mysqltojson;

import ru.sumenkov.mysqltojson.mapper.impl.DataMapperImpl;
import ru.sumenkov.mysqltojson.repository.impl.TableOperationImpl;
import ru.sumenkov.mysqltojson.mapper.impl.JsonMapperImpl;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        try {
            JSONTokener tokener = new JSONTokener(new FileReader("config.json"));
            JSONObject config = new JSONObject(tokener);

            String connectionUrl = String.format("jdbc:mysql://%s:%s/%s", config.get("ip"), config.get("port"), config.get("db"));
            Connection conn = DriverManager.getConnection(connectionUrl, (String) config.get("login"), (String) config.get("password"));

            Date inputDate = new SimpleDateFormat("dd.MM.yyyy").parse("01.10.2022");
            JSONObject json = new JsonMapperImpl().convertArray(new TableOperationImpl(conn, new DataMapperImpl()).readOneDay(inputDate));

            try (FileWriter file = new FileWriter("readSQL.json")) {
                file.write(json.toString(4));
                file.flush();
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Fail open connect", e);
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
