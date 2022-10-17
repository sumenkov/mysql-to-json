package ru.sumenkov.mysqltojson;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ru.sumenkov.mysqltojson.repository.QueryReadTable;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws IOException, org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        JSONObject config = (JSONObject) parser.parse(new FileReader("config.json"));

        String connectionUrl = String.format("jdbc:mysql://%s:%s/%s", config.get("ip"), config.get("port"), config.get("db"));

        try {
            Connection connection = DriverManager.getConnection(connectionUrl, (String) config.get("login"), (String) config.get("password"));
            System.out.println("Соединение с СУБД выполнено.");

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(new QueryReadTable().readDatePeriod(
                    new SimpleDateFormat("dd.MM.yyyy").parse("01.09.2022"),
                    new SimpleDateFormat("dd.MM.yyyy").parse("02.09.2022"),
                    10));

            while (rs.next()) {
                System.out.printf("PTP_NAME: %s, PTP_ID: %d, CNT: %d %n", rs.getString(1), rs.getInt(2), rs.getInt(12));
            }

            connection.close();
            System.out.println("Отключение от СУБД выполнено.");

        } catch (SQLException e) {
            System.out.println("Ошибка SQL !");
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}