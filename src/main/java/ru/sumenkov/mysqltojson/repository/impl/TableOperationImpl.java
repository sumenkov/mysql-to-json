package ru.sumenkov.mysqltojson.repository.impl;

import ru.sumenkov.mysqltojson.mapper.impl.DataMapperImpl;
import ru.sumenkov.mysqltojson.model.InitialModel;
import ru.sumenkov.mysqltojson.repository.TableOperation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableOperationImpl implements TableOperation {
    private static final Logger LOG = Logger.getLogger(TableOperationImpl.class.getName());
    private final Connection conn;
    private  final DataMapperImpl dataMapperImpl;
    private final String COLUMNS = "PTP_NAME,PTP_ID, DT1, ROUTE_NUM, TARIF, PRTYPE, SUMM, CNT, QCNT";

    public TableOperationImpl(Connection conn, DataMapperImpl dataMapperImpl) {
        this.conn = conn;
        this.dataMapperImpl = dataMapperImpl;
    }

    @Override
    public List<InitialModel> readTable(String name) {
        String query = String.format("SELECT %s FROM `%s`", COLUMNS, name);
        return parseAll(query);
    }

    @Override
    public List<InitialModel> readTable(String name, int numberOfLines) {
        return readTable(name, 0, numberOfLines);
    }

    @Override
    public List<InitialModel> readTable(String name, int firstLine, int numberOfLines) {
        String query = String.format("SELECT %s FROM `%s` LIMIT %d, %d", COLUMNS, name, firstLine, numberOfLines);
        return parseAll(query);
    }
    @Override
    public List<InitialModel> readOneDay(Date date) {
        String query = String.format("SELECT %s FROM `%d` WHERE dt1='%s'", COLUMNS, getYear(date), getSfd(date));
        return parseAll(query);
    }
    @Override
    public List<InitialModel> readOneDay(Date date, int numberOfLines) {
        return readOneDay(date, 0, numberOfLines);
    }
    @Override
    public List<InitialModel> readOneDay(Date date, int firstLine, int numberOfLines) {
        String query = String.format("SELECT %s FROM `%d` WHERE dt1='%s' LIMIT %d, %d",
                COLUMNS, getYear(date), getSfd(date), firstLine, numberOfLines);
        return parseAll(query);
    }
    @Override
    public List<InitialModel> readOneMonth(Date date) {
        String query = String.format("SELECT %s FROM `%d` WHERE dt1 LIKE '%s'", COLUMNS, getYear(date), getSfdM(date));
        return parseAll(query);
    }
    @Override
    public List<InitialModel> readOneMonth(Date date, int numberOfLines) {
        return readOneMonth(date, 0 ,numberOfLines);
    }
    @Override
    public List<InitialModel> readOneMonth(Date date, int firstLine, int numberOfLines) {
        String query = String.format("SELECT %s FROM `%d` WHERE dt1 LIKE '%s' LIMIT %d, %d",
                COLUMNS, getYear(date), getSfdM(date), firstLine, numberOfLines);
        return parseAll(query);
    }
    @Override
    public List<InitialModel> readDatePeriod(Date dateStart, Date dateEnd) {
        String query = String.format("SELECT %s FROM `%d` WHERE dt1 BETWEEN '%s' and '%s'",
                COLUMNS, getYear(dateStart), getSfd(dateStart), getSfd(dateEnd));
        return parseAll(query);
    }
    @Override
    public List<InitialModel> readDatePeriod(Date dateStart, Date dateEnd, int numberOfLines) {
        return readDatePeriod(dateStart, dateEnd, 0, numberOfLines);
    }
    @Override
    public List<InitialModel> readDatePeriod(Date dateStart, Date dateEnd, int firstLine, int numberOfLines) {
        String query = String.format("SELECT %s FROM `%d` WHERE dt1 BETWEEN '%s' and '%s' LIMIT %d, %d",
                COLUMNS, getYear(dateStart), getSfd(dateStart), getSfd(dateEnd), firstLine, numberOfLines);
        return parseAll(query);
    }
    private int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    private String getSfd(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    private String getSfdM(Date date) {
        return new SimpleDateFormat("yyyy-MM-%").format(date);
    }

    private List<InitialModel> parseAll(String query) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            List<InitialModel> resul = new ArrayList<>();

            while (rs.next()) {
                resul.add(dataMapperImpl.map(rs));
            }
            return resul;
        } catch (SQLException e) {
            LOG.log(Level.SEVERE, "Fail send request", e);
            return Collections.emptyList();
        }
    }
}
