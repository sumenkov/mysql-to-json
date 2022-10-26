package ru.sumenkov.mysqltojson.mapper;

import org.json.JSONObject;
import ru.sumenkov.mysqltojson.model.InitialModel;

import java.util.List;

public interface JsonMapper {
    JSONObject convertArray(List<InitialModel> allLines);
}
