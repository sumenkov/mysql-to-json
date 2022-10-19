package ru.sumenkov.mysqltojson.mapper.impl;

import org.json.JSONObject;
import ru.sumenkov.mysqltojson.mapper.JsonMapper;
import ru.sumenkov.mysqltojson.model.InitialModel;

import java.util.*;

public class JsonMapperImpl implements JsonMapper {
    @Override
    public JSONObject convertObject(InitialModel line) {
        Integer ptpId = line.getPtpId();

        HashMap<Integer, HashMap<Object, Object>> averageLevel = new HashMap<>();
        averageLevel.put(ptpId, new HashMap<>());
            if (!averageLevel.get(ptpId).containsKey("ptpName"))
                averageLevel.get(ptpId).put("ptpName", line.getPtpName());
        averageLevel.get(ptpId).put(line.getRouteNum(), lowerLevel(line));

        HashMap<Date, Object> result = new HashMap<>();
        result.put(line.getDt1(), averageLevel);
        return new JSONObject(result);
    }

    @Override
    public JSONObject convertArray(List<InitialModel> allLines) {
        HashMap<Date, HashMap<Integer, HashMap<Object, Object>>> result = new HashMap<>();

        for (InitialModel line: allLines) {
            Date dt1 = line.getDt1();
            Double prType = line.getPrType();
            Integer ptpId = line.getPtpId();
            String routeNum = line.getRouteNum();

            if (!result.containsKey(dt1)) {
                HashMap<Integer, HashMap<Object, Object>> averageLevel = new HashMap<>();
                averageLevel.put(ptpId, new HashMap<>());
                if (!averageLevel.get(ptpId).containsKey("ptpName"))
                    averageLevel.get(ptpId).put("ptpName", line.getPtpName());
                averageLevel.get(ptpId).put(line.getRouteNum(), lowerLevel(line));

                result.put(line.getDt1(), averageLevel);
            } else {
                if (!result.get(dt1).containsKey(ptpId)) {
                    result.get(dt1).put(ptpId, new HashMap<>());
                    if (!result.get(dt1).get(ptpId).containsKey("ptpName"))
                        result.get(dt1).get(ptpId).put("ptpName", line.getPtpName());
                    result.get(dt1).get(ptpId).put(routeNum, lowerLevel(line));
                } else {
                    if (!result.get(dt1).get(ptpId).containsKey(routeNum)) {
                        result.get(dt1).get(ptpId).put(routeNum, lowerLevel(line));
                    } else {
                        HashMap<Double, HashMap<Object, Object>> lowerLevel = (HashMap<Double, HashMap<Object, Object>>) result.get(dt1).get(ptpId).get(routeNum);
                        if (!lowerLevel.containsKey(prType)) {
//                            HashMap<Object, Object> lowerLevel = new HashMap<>();
                            lowerLevel.put(prType, new HashMap<>());
                            lowerLevel.get(prType).put("tarif", line.getTarif());
                            lowerLevel.get(prType).put("summ", line.getSumm());
                            lowerLevel.get(prType).put("cnt", line.getCnt());
                            lowerLevel.get(prType).put("qCnt", line.getQCnt());
                        } else {
                            Double summ = (Double) lowerLevel.get(prType).get("summ");
                            int cnt = (int) lowerLevel.get(prType).get("cnt");
                            int qCnt = (int) lowerLevel.get(prType).get("qCnt");

                            lowerLevel.get(prType).put("summ", line.getSumm() + summ);
                            lowerLevel.get(prType).put("cnt", line.getCnt() + cnt);
                            lowerLevel.get(prType).put("qCnt", line.getQCnt() + qCnt);
                        }
                    }
                }
            }
        }
        return new JSONObject(result);
    }

    private HashMap<Double, HashMap<Object, Object>> lowerLevel(InitialModel line)  {
        Double prType = line.getPrType();
        HashMap<Double, HashMap<Object, Object>> lowerMap = new HashMap<>();
        lowerMap.put(prType, new HashMap<>());
        lowerMap.get(prType).put("tarif", line.getTarif());
        lowerMap.get(prType).put("summ", line.getSumm());
        lowerMap.get(prType).put("cnt", line.getCnt());
        lowerMap.get(prType).put("qCnt", line.getQCnt());

        return lowerMap;
    }
}
