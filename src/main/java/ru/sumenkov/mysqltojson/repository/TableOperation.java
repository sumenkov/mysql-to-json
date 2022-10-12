package ru.sumenkov.mysqltojson.repository;

import ru.sumenkov.mysqltojson.model.TableSQLModel;

import java.sql.SQLException;

public interface TableOperation {
    void readTable() throws SQLException; // SELECT * FROM '2022'
    void getOneDay() throws SQLException; // SELECT * FROM '2022' WHERE dt1='2022-07-20'
    void getMonth() throws SQLException; // SELECT * FROM '2022' WHERE dt1 LIKE '2022-07-%'
    void getDatePeriod() throws SQLException; // SELECT * FROM '2022' WHERE dt1 BETWEEN '2022-07-20' and '2022-07-31'
}
