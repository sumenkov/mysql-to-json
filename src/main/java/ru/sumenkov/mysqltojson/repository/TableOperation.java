package ru.sumenkov.mysqltojson.repository;

import java.util.Date;

public interface TableOperation {
    String readTable(String name);
    String readTable(String name, int numberOfLines);
    String readTable(String name, int firstLine, int numberOfLines);
    String readOneDay(Date date);
    String readOneDay(Date date, int numberOfLines);
    String readOneDay(Date date, int firstLine, int numberOfLines);
    String readOneMonth(Date date);
    String readOneMonth(Date date, int numberOfLines);
    String readOneMonth(Date date, int firstLine, int numberOfLines);
    String readDatePeriod(Date dateStart, Date dateEnd);
    String readDatePeriod(Date dateStart, Date dateEnd, int numberOfLines);
    String readDatePeriod(Date dateStart, Date dateEnd, int firstLine, int numberOfLines);
}
