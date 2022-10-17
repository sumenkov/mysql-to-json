package ru.sumenkov.mysqltojson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import ru.sumenkov.mysqltojson.repository.QueryReadTable;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject config = (JSONObject) parser.parse(new FileReader("config.json"));

            String connectionUrl = String.format("jdbc:mysql://%s:%s/%s", config.get("ip"), config.get("port"), config.get("db"));
            Connection connection = DriverManager.getConnection(connectionUrl, (String) config.get("login"), (String) config.get("password"));
            System.out.println("Соединение с СУБД выполнено.");

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(new QueryReadTable().readDatePeriod(
                    new SimpleDateFormat("dd.MM.yyyy").parse("01.09.2022"),
                    new SimpleDateFormat("dd.MM.yyyy").parse("02.09.2022"),
                    10));

            JSONArray linesList = new JSONArray();

            while (rs.next()) {
                System.out.printf("PTP_NAME: %s, PTP_ID: %d, CNT: %d %n", rs.getString(1), rs.getInt(2), rs.getInt(12));
                JSONObject lineDetails = new JSONObject(); // надо завернуть в модель
                lineDetails.put("ptpName", rs.getString(1).replace("\"", ""));
                lineDetails.put("ptpId", rs.getInt(2));
                lineDetails.put("cnt", rs.getInt(12));
                linesList.add(lineDetails);
            }

            FileWriter newFile = new FileWriter("readSQL.json");
            newFile.write(linesList.toJSONString());
            newFile.flush();

            connection.close();
            System.out.println("Отключение от СУБД выполнено.");

        } catch (SQLException e) {
            System.out.println("Ошибка SQL !");
            e.printStackTrace();
        } catch (ParseException | IOException | org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
