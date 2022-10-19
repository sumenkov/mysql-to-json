package ru.sumenkov.mysqltojson.mapper;

import org.json.JSONObject;
import ru.sumenkov.mysqltojson.model.InitialModel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface JsonMapper {

    JSONObject convertObject(InitialModel line);
    JSONObject convertArray(List<InitialModel> allLines);
}
