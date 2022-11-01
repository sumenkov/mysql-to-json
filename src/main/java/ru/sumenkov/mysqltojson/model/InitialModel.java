package ru.sumenkov.mysqltojson.model;

import java.util.Date;

public class InitialModel {
    // описываем только те атрибуты, которые будем использовать (вся таблица не нужна)
    private String ptpName; // 1 column
    private int ptpId; // 2 column
    private Date dt; // 4 column
    private String routeNum; // 7 column
    private double tarif; // 9 column
    private double prType; // 10 column
    private double summ; // 11 column
    private int cnt; // 12 column
    private int qCnt; // 13 column

    public InitialModel() {
    }

    public InitialModel(String ptpName, int ptpId, Date dt, String routeNum, double tarif, double prType, double summ,
                        int cnt, int qCnt) {
        this.ptpName = ptpName;
        this.ptpId = ptpId;
        this.dt = dt;
        this.routeNum = routeNum;
        this.tarif = tarif;
        this.prType = prType;
        this.summ = summ;
        this.cnt = cnt;
        this.qCnt = qCnt;
    }


    public String getPtpName() {
        return ptpName;
    }

    public void setPtpName(String ptpName) {
        this.ptpName = ptpName;
    }

    public int getPtpId() {
        return ptpId;
    }

    public void setPtpId(int ptpId) {
        this.ptpId = ptpId;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public String getRouteNum() {
        return routeNum;
    }

    public void setRouteNum(String routeNum) {
        this.routeNum = routeNum;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public double getPrType() {
        return prType;
    }

    public void setPrType(float prType) {
        this.prType = prType;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm(float summ) {
        this.summ = summ;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getQCnt() {
        return qCnt;
    }

    public void setQCnt(int qCnt) {
        this.qCnt = qCnt;
    }
}
