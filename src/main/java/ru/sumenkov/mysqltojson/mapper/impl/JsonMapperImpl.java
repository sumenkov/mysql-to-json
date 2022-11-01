package ru.sumenkov.mysqltojson.mapper.impl;

import org.json.JSONObject;
import ru.sumenkov.mysqltojson.mapper.JsonMapper;
import ru.sumenkov.mysqltojson.model.InitialModel;

import java.util.*;

public class JsonMapperImpl implements JsonMapper {
    HashMap<Object, Object> result = new HashMap<>();
    Date dt;
    Integer ptpId, cnt, qCnt;
    String ptpName, routeNum;
    Double tarif, prType, summ;

    @Override
    public JSONObject convertArray(List<InitialModel> allLines) {

        for (InitialModel line: allLines) {
            dt = line.getDt();
            ptpId = line.getPtpId();
            tarif = line.getTarif();
            routeNum = line.getRouteNum();
            prType = line.getPrType();
            summ = line.getSumm();
            cnt = line.getCnt();
            qCnt = line.getQCnt();
            ptpName = line.getPtpName();

            if (!result.containsKey(dt)) {
                result.put(dt, dt1Result());
            } else {
                result.put(dt, dt1Result(new HashMap<>((Map<?, ?>) result.get(dt))));
            }
        }
        return new JSONObject(result);
    }

    private HashMap<Object, Object> dt1Result() {

        HashMap<Object, Object> hasDt1 = new HashMap<>();
        hasDt1.put(ptpId, ptpIdResult());

        return hasDt1;
    }

    private HashMap<Object, Object> dt1Result(HashMap<Object, Object> hasDt1) {

        if (!hasDt1.containsKey(ptpId)) {
            hasDt1.put(ptpId, ptpIdResult());
        } else {
            hasDt1.put(ptpId, ptpIdResult(new HashMap<>((Map<?, ?>) hasDt1.get(ptpId))));
        }

        return hasDt1;
    }

    private HashMap<Object, Object> ptpIdResult() {

        HashMap<Object, Object> hasPtpId = new HashMap<>();
        hasPtpId.put("ptpName", ptpName);
        hasPtpId.put(tarif, tarifResult());

        return hasPtpId;
    }

    private HashMap<Object, Object> ptpIdResult(HashMap<Object, Object> hasPtpId) {

        if (!hasPtpId.containsKey(tarif)) {
            hasPtpId.put(tarif, tarifResult());
        } else {
            hasPtpId.put(tarif, tarifResult(new HashMap<>((Map<?, ?>) hasPtpId.get(tarif))));
        }

        return hasPtpId;
    }

    private HashMap<Object, Object> tarifResult() {

        HashMap<Object, Object> hasTarif = new HashMap<>();
        hasTarif.put(routeNum, prTypeResult());

        return hasTarif;
    }

    private HashMap<Object, Object> tarifResult(HashMap<Object, Object> hasTarif) {
        if (!hasTarif.containsKey(routeNum)) {
            hasTarif.put(routeNum, prTypeResult());
        } else {
            hasTarif.put(routeNum, prTypeResult(new HashMap<>((Map<?, ?>) hasTarif.get(routeNum))));
        }

        return hasTarif;
    }

    private HashMap<Object, Object> prTypeResult()  {

        HashMap<Object, Object> newHasMap = new HashMap<>();
        newHasMap.put(prType, scqResult());

        return newHasMap;
    }

    private HashMap<Object, Object> prTypeResult(HashMap<Object, Object> hasRouteNum)  {

        if (!hasRouteNum.containsKey(prType)) {
            hasRouteNum.put(prType, scqResult());
        } else {
            hasRouteNum.put(prType, scqResult(new HashMap<>((Map<?, ?>) hasRouteNum.get(prType))));
        }

        return hasRouteNum;
    }

    private HashMap<Object, Object> scqResult() {

        HashMap<Object, Object> hasSCQ = new HashMap<>();
        hasSCQ.put("summ", summ);
        hasSCQ.put("cnt", cnt);
        hasSCQ.put("qCnt", qCnt);

        return hasSCQ;
    }

    private HashMap<Object, Object> scqResult(HashMap<Object, Object> hasSCQ) {

        Double s = (Double) hasSCQ.get("summ");
        int c = (int) hasSCQ.get("cnt");
        int q = (int) hasSCQ.get("qCnt");

        hasSCQ.put("summ", s + summ);
        hasSCQ.put("cnt", c + cnt);
        hasSCQ.put("qCnt", q + qCnt);

        return hasSCQ;
    }
}
