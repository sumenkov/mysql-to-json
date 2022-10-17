package ru.sumenkov.mysqltojson;

import ru.sumenkov.mysqltojson.repository.QueryReadTable;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        String connectionUrl = "jdbc:mysql://10.195.70.16:3306/db_trday";

        try {
            Connection connection = DriverManager.getConnection(connectionUrl, "pobezhimov", "QfxWjbLD");
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