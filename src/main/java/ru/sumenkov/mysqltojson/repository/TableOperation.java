package ru.sumenkov.mysqltojson.repository;

import ru.sumenkov.mysqltojson.model.InitialModel;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface TableOperation {
    List<InitialModel> readTable(String name);

    List<InitialModel> readOneDay(Date date);

    List<InitialModel> readOneMonth(Date date);

    List<InitialModel> readDatePeriod(Date dateStart, Date dateEnd);
}
