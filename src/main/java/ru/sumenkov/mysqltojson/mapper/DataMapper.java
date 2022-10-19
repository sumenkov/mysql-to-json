package ru.sumenkov.mysqltojson.mapper;

import ru.sumenkov.mysqltojson.model.InitialModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataMapper {

    InitialModel map(ResultSet rs) throws SQLException;
}
