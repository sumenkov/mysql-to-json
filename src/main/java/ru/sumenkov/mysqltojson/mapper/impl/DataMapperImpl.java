package ru.sumenkov.mysqltojson.mapper.impl;

import ru.sumenkov.mysqltojson.mapper.DataMapper;
import ru.sumenkov.mysqltojson.model.InitialModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataMapperImpl implements DataMapper {
    @Override
    public InitialModel map(ResultSet rs) throws SQLException {
        return new InitialModel(
                rs.getString(1).replace("\"", ""),
                rs.getInt(2),
                rs.getDate(3),
                rs.getString(4),
                rs.getDouble(5),
                rs.getDouble(6),
                rs.getDouble(7),
                rs.getInt(8),
                rs.getInt(9));
    }
}
