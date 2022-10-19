package ru.sumenkov.mysqltojson.repository.impl;

import ru.sumenkov.mysqltojson.repository.TableOperation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TableOperationImpl implements TableOperation {
    private final String columnsList = "PTP_NAME,PTP_ID, DT1, ROUTE_NUM, TARIF, PRTYPE, SUMM, CNT, QCNT";
    @Override
    public String readTable(String name) {
        return String.format("SELECT %s FROM `%s`", columnsList, name);
    }

    @Override
    public String readTable(String name, int numberOfLines) {
        return readTable(name, 0, numberOfLines);
    }

    @Override
    public String readTable(String name, int firstLine, int numberOfLines)  {
        return String.format("SELECT %s FROM `%s` LIMIT %d, %d", columnsList, name, firstLine, numberOfLines);
    }
    @Override
    public String readOneDay(Date date) {
        return String.format("SELECT %s FROM `%d` WHERE dt1='%s'", columnsList, getYear(date), getSfd(date));
    }
    @Override
    public String readOneDay(Date date, int numberOfLines) {
        return readOneDay(date, 0, numberOfLines);
    }
    @Override
    public String readOneDay(Date date, int firstLine, int numberOfLines) {
        return String.format("SELECT %s FROM `%d` WHERE dt1='%s' LIMIT %d, %d",
                columnsList, getYear(date), getSfd(date), firstLine, numberOfLines);
    }
    @Override
    public String readOneMonth(Date date) {
        String sfd = new SimpleDateFormat("yyyy-MM-%").format(date);
        return String.format("SELECT %s FROM `%d` WHERE dt1 LIKE '%s'", columnsList, getYear(date), sfd);
    }
    @Override
    public String readOneMonth(Date date, int numberOfLines) {
        return readOneMonth(date, 0 ,numberOfLines);
    }
    @Override
    public String readOneMonth(Date date, int firstLine, int numberOfLines) {
        String sfd = new SimpleDateFormat("yyyy-MM-%").format(date);
        return String.format("SELECT %s FROM `%d` WHERE dt1 LIKE '%s' LIMIT %d, %d",
                columnsList, getYear(date), sfd, firstLine, numberOfLines);
    }
    @Override
    public String readDatePeriod(Date dateStart, Date dateEnd) {
        return String.format("SELECT %s FROM `%d` WHERE dt1 BETWEEN '%s' and '%s'",
                columnsList, getYear(dateStart), getSfd(dateStart), getSfd(dateEnd));
    }
    @Override
    public String readDatePeriod(Date dateStart, Date dateEnd, int numberOfLines) {
        return readDatePeriod(dateStart, dateEnd, 0, numberOfLines);
    }
    @Override
    public String readDatePeriod(Date dateStart, Date dateEnd, int firstLine, int numberOfLines) {
        return String.format("SELECT %s FROM `%d` WHERE dt1 BETWEEN '%s' and '%s' LIMIT %d, %d",
                columnsList, getYear(dateStart), getSfd(dateStart), getSfd(dateEnd), firstLine, numberOfLines);
    }
    private int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    private String getSfd(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
