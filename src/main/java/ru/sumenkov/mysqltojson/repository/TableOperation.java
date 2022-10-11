package ru.sumenkov.mysqltojson.repository;

import java.sql.SQLException;

public interface TableOperation {
    void readTable() throws SQLException;
}
