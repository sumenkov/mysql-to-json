package ru.sumenkov.mysqltojson.repository;

import ru.sumenkov.mysqltojson.model.InitialModel;

import java.util.Date;
import java.util.List;

public interface TableOperation {
    List<InitialModel> readTable(String name);
    List<InitialModel> readTable(String name, int numberOfLines);
    List<InitialModel> readTable(String name, int firstLine, int numberOfLines);
    List<InitialModel> readOneDay(Date date);
    List<InitialModel> readOneDay(Date date, int numberOfLines);
    List<InitialModel> readOneDay(Date date, int firstLine, int numberOfLines);
    List<InitialModel> readOneMonth(Date date);
    List<InitialModel> readOneMonth(Date date, int numberOfLines);
    List<InitialModel> readOneMonth(Date date, int firstLine, int numberOfLines);
    List<InitialModel> readDatePeriod(Date dateStart, Date dateEnd);
    List<InitialModel> readDatePeriod(Date dateStart, Date dateEnd, int numberOfLines);
    List<InitialModel> readDatePeriod(Date dateStart, Date dateEnd, int firstLine, int numberOfLines);
}
