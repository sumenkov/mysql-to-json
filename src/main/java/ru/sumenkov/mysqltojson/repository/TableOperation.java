package ru.sumenkov.mysqltojson.repository;

import java.sql.SQLException;

public interface TableOperation {
    void readTable() throws SQLException;
    void getOneDay() throws SQLException;
    void getMount() throws SQLException;
    void getPeriod() throws SQLException;
}
