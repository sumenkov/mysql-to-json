package ru.sumenkov.mysqltojson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String connectionUrl = "jdbc:mysql://10.195.70.16:3306/db_trday";

        try {
            Connection connection = DriverManager.getConnection(connectionUrl, "pobezhimov", "QfxWjbLD");
            System.out.println("Соединение с СУБД выполнено.");

            connection.close();
            System.out.println("Отключение от СУБД выполнено.");

        } catch (SQLException e) {
            System.out.println("Ошибка SQL !");
            e.printStackTrace();
        }
    }
}