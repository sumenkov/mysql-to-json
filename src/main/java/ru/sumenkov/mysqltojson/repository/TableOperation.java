package ru.sumenkov.mysqltojson.repository;

public interface TableOperation {
    String readTable(String name); // SELECT * FROM `2022`
    String readTable(String name, int numberOfLines); // SELECT * FROM `2022` LIMIT 0, 1000
    String readTable(String name, int firstLine, int numberOfLines); // SELECT * FROM `2022` LIMIT 10, 1000
}
