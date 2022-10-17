package ru.sumenkov.mysqltojson;

import org.json.JSONObject;
import org.json.JSONTokener;
import ru.sumenkov.mysqltojson.model.TableSQLModel;
import ru.sumenkov.mysqltojson.repository.QueryReadTable;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        try {
            JSONTokener tokener = new JSONTokener(new FileReader("config.json"));
            JSONObject config = new JSONObject(tokener);

                    String connectionUrl = String.format("jdbc:mysql://%s:%s/%s", config.get("ip"), config.get("port"), config.get("db"));
            Connection connection = DriverManager.getConnection(connectionUrl, (String) config.get("login"), (String) config.get("password"));
            System.out.println("Соединение с СУБД выполнено.");

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(new QueryReadTable().readDatePeriod(
                    new SimpleDateFormat("dd.MM.yyyy").parse("01.09.2022"),
                    new SimpleDateFormat("dd.MM.yyyy").parse("02.09.2022"),
                    100));

            HashMap<Date, HashMap<Integer, HashMap<String, Object>>> dateAndId = new HashMap<>();

            while (rs.next()) {
                TableSQLModel line = new TableSQLModel(
                        rs.getString(1).replace("\"", ""),
                        rs.getInt(2),
                        rs.getDate(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getInt(8),
                        rs.getInt(9));

                if (dateAndId.containsKey(line.getDt1())){
                    if (dateAndId.get(line.getDt1()).containsKey(line.getPtpId())){
                        dateAndId.get(line.getDt1()).get(line.getPtpId()).put("ptpName", line.getPtpName());
                        if (dateAndId.get(line.getDt1()).get(line.getPtpId()).containsKey(line.getRouteNum())) {
                            HashMap<Double, Object> nextMap = (HashMap<Double, Object>) dateAndId.get(line.getDt1()).get(line.getPtpId()).get(line.getRouteNum());
                            if (nextMap.containsKey(line.getPrType())) {
                                HashMap<String, Object> nextNextMap = (HashMap<String, Object>) ((HashMap<?, ?>) dateAndId.get(line.getDt1()).get(line.getPtpId()).get(line.getRouteNum())).get(line.getPrType());
                                nextNextMap.put("summ", line.getSumm());
                                nextNextMap.put("cnt", line.getCnt());
                                nextNextMap.put("qcnt", line.getQCnt());
                            } else {
                                nextMap.put(line.getPrType(), new HashMap<>());
                                HashMap<String, Object> nextNextMap = (HashMap<String, Object>) ((HashMap<?, ?>) dateAndId.get(line.getDt1()).get(line.getPtpId()).get(line.getRouteNum())).get(line.getPrType());
                                nextNextMap.put("summ", line.getSumm());
                                nextNextMap.put("cnt", line.getCnt());
                                nextNextMap.put("qcnt", line.getQCnt());
                            }
                        } else {
                            dateAndId.get(line.getDt1()).get(line.getPtpId()).put(line.getRouteNum(), new HashMap<>());
                            HashMap<Double, Object> nextMap = (HashMap<Double, Object>) dateAndId.get(line.getDt1()).get(line.getPtpId()).get(line.getRouteNum());
                            nextMap.put(line.getPrType(), new HashMap<>());
                            HashMap<String, Object> nextNextMap = (HashMap<String, Object>) ((HashMap<?, ?>) dateAndId.get(line.getDt1()).get(line.getPtpId()).get(line.getRouteNum())).get(line.getPrType());
                            nextNextMap.put("summ", line.getSumm());
                            nextNextMap.put("cnt", line.getCnt());
                            nextNextMap.put("qcnt", line.getQCnt());
                        }
                    } else {
                        dateAndId.get(line.getDt1()).put(line.getPtpId(), new HashMap<>());
                        dateAndId.get(line.getDt1()).get(line.getPtpId()).put("ptpName", line.getPtpName());
                        dateAndId.get(line.getDt1()).get(line.getPtpId()).put(line.getRouteNum(), new HashMap<>());
                        HashMap<Double, Object> nextMap = (HashMap<Double, Object>) dateAndId.get(line.getDt1()).get(line.getPtpId()).get(line.getRouteNum());
                        nextMap.put(line.getPrType(), new HashMap<>());
                        HashMap<String, Object> nextNextMap = (HashMap<String, Object>) ((HashMap<?, ?>) dateAndId.get(line.getDt1()).get(line.getPtpId()).get(line.getRouteNum())).get(line.getPrType());
                        nextNextMap.put("summ", line.getSumm());
                        nextNextMap.put("cnt", line.getCnt());
                        nextNextMap.put("qcnt", line.getQCnt());
                    }
                } else {
                    dateAndId.put(line.getDt1(), new HashMap<>());
                    dateAndId.get(line.getDt1()).put(line.getPtpId(), new HashMap<>());
                    dateAndId.get(line.getDt1()).get(line.getPtpId()).put("ptpName", line.getPtpName());
                    dateAndId.get(line.getDt1()).get(line.getPtpId()).put(line.getRouteNum(), new HashMap<>());
                    HashMap<Double, Object> nextMap = (HashMap<Double, Object>) dateAndId.get(line.getDt1()).get(line.getPtpId()).get(line.getRouteNum());
                    nextMap.put(line.getPrType(), new HashMap<>());
                    HashMap<String, Object> nextNextMap = (HashMap<String, Object>) ((HashMap<?, ?>) dateAndId.get(line.getDt1()).get(line.getPtpId()).get(line.getRouteNum())).get(line.getPrType());
                    nextNextMap.put("summ", line.getSumm());
                    nextNextMap.put("cnt", line.getCnt());
                    nextNextMap.put("qcnt", line.getQCnt());
                }
            }
            System.out.println(dateAndId);

            connection.close();
            System.out.println("Отключение от СУБД выполнено.");

        } catch (SQLException e) {
            System.out.println("Ошибка SQL !");
            e.printStackTrace();
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
