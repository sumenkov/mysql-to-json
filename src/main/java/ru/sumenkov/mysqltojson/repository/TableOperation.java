package ru.sumenkov.mysqltojson.repository;

import java.sql.SQLException;
import java.util.Date;

public interface TableOperation {
    void readTable(int year) throws SQLException; // SELECT * FROM '2022'
    void readTable(int year, int numberOfLines) throws SQLException; // SELECT * FROM '2022' LIMIT 0, 1000
    void readTable(int year, int firstLine, int numberOfLines) throws SQLException; // SELECT * FROM '2022' LIMIT 10, 1000

    void readOneDay(Date date) throws SQLException; // SELECT * FROM '2022' WHERE dt1='2022-07-20'
    void readOneDay(Date date, int numberOfLines) throws SQLException; // SELECT * FROM '2022' WHERE dt1='2022-07-20' LIMIT 0, 1000
    void readOneDay(Date date, int firstLine, int numberOfLines) throws SQLException; // SELECT * FROM '2022' WHERE dt1='2022-07-20' LIMIT 10, 1000

    void readOneMonth(Date date) throws SQLException; // SELECT * FROM '2022' WHERE dt1 LIKE '2022-07-%'
    void readOneMonth(Date date, int numberOfLines) throws SQLException; // SELECT * FROM '2022' WHERE dt1 LIKE '2022-07-%' LIMIT 0, 1000
    void readOneMonth(Date date, int firstLine, int numberOfLines) throws SQLException; // SELECT * FROM '2022' WHERE dt1 LIKE '2022-07-%' LIMIT 10, 1000

    void readDatePeriod(Date dateStart, Date dateEnd) throws SQLException; // SELECT * FROM '2022' WHERE dt1 BETWEEN '2022-07-20' and '2022-07-31'
    void readDatePeriod(Date dateStart, Date dateEnd, int numberOfLines) throws SQLException; // SELECT * FROM '2022' WHERE dt1 BETWEEN '2022-07-20' and '2022-07-31' LIMIT 0, 1000
    void readDatePeriod(Date dateStart, Date dateEnd, int firstLine, int numberOfLines) throws SQLException; // SELECT * FROM '2022' WHERE dt1 BETWEEN '2022-07-20' and '2022-07-31' LIMIT 10, 1000
}
