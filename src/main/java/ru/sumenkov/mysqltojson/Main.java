package ru.sumenkov.mysqltojson;

import org.apache.commons.cli.*;
import ru.sumenkov.mysqltojson.mapper.JsonMapper;
import ru.sumenkov.mysqltojson.mapper.impl.DataMapperImpl;
import ru.sumenkov.mysqltojson.repository.TableOperation;
import ru.sumenkov.mysqltojson.repository.impl.TableOperationImpl;
import ru.sumenkov.mysqltojson.mapper.impl.JsonMapperImpl;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        try {
            CommandLineParser commandLineParser = new DefaultParser();
            Options options = new LaunchOptions().launchOptions();

            if (args.length == 0) helper(options);

            CommandLine cl = commandLineParser.parse(options, args);

            JSONTokener tokener = new JSONTokener(new FileReader("config.json"));
            JSONObject config = new JSONObject(tokener);
            String connectionUrl = String.format("jdbc:mysql://%s:%s/%s", config.get("ip"), config.get("port"), config.get("db"));
            Connection conn = DriverManager.getConnection(connectionUrl, (String) config.get("login"), (String) config.get("password"));

            JSONObject json = new JSONObject();
            JsonMapper jsonMapper = new JsonMapperImpl();
            TableOperation tableOperation = new TableOperationImpl(conn, new DataMapperImpl());
            String nameFile = null;

            if (cl.hasOption("readtable")) {
                nameFile = cl.getOptionValue("readtable");
                json = jsonMapper.convertArray(tableOperation.readTable(nameFile));
            } else if (cl.hasOption("readday")) {
                nameFile = cl.getOptionValue("readday");
                json = jsonMapper.convertArray(tableOperation.readOneDay(dateMapper(nameFile)));
            } else if (cl.hasOption("readmonth")) {
                nameFile = cl.getOptionValue("readmonth");
                json = jsonMapper.convertArray(tableOperation.readOneMonth(dateMapper(nameFile)));
            } else if (cl.hasOption("readperiod")) {
                nameFile = cl.getOptionValue("readperiod");
                json = jsonMapper.convertArray(tableOperation.readDatePeriod(
                        dateMapper(nameFile.split("-")[0]), dateMapper(nameFile.split("-")[1])));
            } else {
                helper(options);
            }

            saveFile(json, nameFile);

        } catch (SQLException e) {
            log.log(Level.SEVERE, "Fail open connect", e);
        } catch (org.apache.commons.cli.ParseException e) {
            log.log(Level.SEVERE, "Fail parse options", e);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Fail read config", e);
        } catch (ParseException e) {
            log.log(Level.SEVERE, "Fail date mapper", e);
        }
    }

    private static Date dateMapper(String date) throws ParseException {
        return new SimpleDateFormat("dd.MM.yyyy").parse(date);
    }

    private static void saveFile(JSONObject json, String nameFile) {
        try (FileWriter file = new FileWriter(String.format("%s.json", nameFile))) {
            file.write(json.toString(4));
            file.flush();
        } catch (IOException e) {
            log.log(Level.SEVERE, "Fail save file", e);
        }
    }

    private static void helper(Options options){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("mysql-to-json", options, true);
        System.exit(0);
    }
}
