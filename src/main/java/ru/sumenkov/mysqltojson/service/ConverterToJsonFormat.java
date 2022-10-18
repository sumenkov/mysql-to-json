package ru.sumenkov.mysqltojson.service;

import ru.sumenkov.mysqltojson.model.InitialModel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ConverterToJsonFormat {
    public HashMap<Date, Object> convertObject(InitialModel line) {
        Double prType = line.getPrType();
        Integer ptpId = line.getPtpId();

        HashMap<Double, HashMap<String, Object>> lowerLevel = new HashMap<>();
        lowerLevel.put(prType, new HashMap<>());
        lowerLevel.get(prType).put("tarif", line.getTarif());
        lowerLevel.get(prType).put("summ", line.getSumm());
        lowerLevel.get(prType).put("cnt", line.getCnt());
        lowerLevel.get(prType).put("qCnt", line.getQCnt());

        HashMap<Integer, HashMap<Object, Object>> averageLevel = new HashMap<>();
        averageLevel.put(ptpId, new HashMap<>());
            if (!averageLevel.get(ptpId).containsKey("ptpName"))
                averageLevel.get(ptpId).put("ptpName", line.getPtpName());
        averageLevel.get(ptpId).put(line.getRouteNum(), lowerLevel);

        HashMap<Date, Object> result = new HashMap<>();
        result.put(line.getDt1(), averageLevel);
        return result;
    }

    public void convertArray(List<HashMap<Date, Object>> allLines){
    }
}
