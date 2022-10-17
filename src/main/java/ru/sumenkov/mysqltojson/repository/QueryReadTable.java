package ru.sumenkov.mysqltojson.repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class QueryReadTable implements TableOperation{

    @Override
    public String readTable(String name) {
        return String.format("SELECT * FROM `%s`", name);
    }

    @Override
    public String readTable(String name, int numberOfLines) {
        return readTable(name, 0, numberOfLines);
    }

    @Override
    public String readTable(String name, int firstLine, int numberOfLines)  {
        return String.format("SELECT * FROM `%s` LIMIT %d, %d", name, firstLine, numberOfLines);
    }

    public String readOneDay(Date date) {
        return String.format("SELECT * FROM `%d` WHERE dt1='%s'", getYear(date), getSfd(date));
    }

    public String readOneDay(Date date, int numberOfLines) {
        return readOneDay(date, 0, numberOfLines);
    }

    public String readOneDay(Date date, int firstLine, int numberOfLines) {
        return String.format("SELECT * FROM `%d` WHERE dt1='%s' LIMIT %d, %d",
                getYear(date), getSfd(date), firstLine, numberOfLines);
    }

    public String readOneMonth(Date date) {
        String sfd = new SimpleDateFormat("yyyy-MM-%").format(date);
        return String.format("SELECT * FROM `%d` WHERE dt1 LIKE '%s'", getYear(date), sfd);
    }

    public String readOneMonth(Date date, int numberOfLines) {
        return readOneMonth(date, 0 ,numberOfLines);
    }

    public String readOneMonth(Date date, int firstLine, int numberOfLines) {
        String sfd = new SimpleDateFormat("yyyy-MM-%").format(date);
        return String.format("SELECT * FROM `%d` WHERE dt1 LIKE '%s' LIMIT %d, %d",
                getYear(date), sfd, firstLine, numberOfLines);
    }

    public String readDatePeriod(Date dateStart, Date dateEnd) {
        return String.format("SELECT * FROM `%d` WHERE dt1 BETWEEN '%s' and '%s'",
                getYear(dateStart), getSfd(dateStart), getSfd(dateEnd));
    }

    public String readDatePeriod(Date dateStart, Date dateEnd, int numberOfLines) {
        return readDatePeriod(dateStart, dateEnd, 0, numberOfLines);
    }

    public String readDatePeriod(Date dateStart, Date dateEnd, int firstLine, int numberOfLines) {
        return String.format("SELECT * FROM `%d` WHERE dt1 BETWEEN '%s' and '%s' LIMIT %d, %d",
                getYear(dateStart), getSfd(dateStart), getSfd(dateEnd), firstLine, numberOfLines);
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
