package ru.sumenkov.mysqltojson;

import org.json.JSONObject;
import org.json.JSONTokener;
import ru.sumenkov.mysqltojson.model.InitialModel;
import ru.sumenkov.mysqltojson.repository.impl.TableOperationImpl;
import ru.sumenkov.mysqltojson.mapper.ConverterToJsonFormat;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            JSONTokener tokener = new JSONTokener(new FileReader("config.json"));
            JSONObject config = new JSONObject(tokener);

                    String connectionUrl = String.format("jdbc:mysql://%s:%s/%s", config.get("ip"), config.get("port"), config.get("db"));
            Connection connection = DriverManager.getConnection(connectionUrl, (String) config.get("login"), (String) config.get("password"));
            System.out.println("Соединение с СУБД выполнено.");

            Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery(new QueryReadTable().readDatePeriod(
//                    new SimpleDateFormat("dd.MM.yyyy").parse("01.09.2022"),
//                    new SimpleDateFormat("dd.MM.yyyy").parse("02.09.2022"), 10));
            ResultSet rs = stmt.executeQuery(new TableOperationImpl().readOneMonth(new SimpleDateFormat("dd.MM.yyyy").parse("01.09.2022"), 10));

            List<InitialModel> allLines = new ArrayList<>();
            while (rs.next()) {
                InitialModel line = new InitialModel(
                        rs.getString(1).replace("\"", ""),
                        rs.getInt(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getInt(9));
                allLines.add(line);
            }

            JSONObject json = new JSONObject(new ConverterToJsonFormat().convertArray(allLines));

            FileWriter file = new FileWriter("readSQL.json");
            file.write(json.toString(4));
            file.flush();

            connection.close();
            System.out.println("Отключение от СУБД выполнено.");

        } catch (SQLException e) {
            System.out.println("Ошибка SQL !");
            e.printStackTrace();
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
