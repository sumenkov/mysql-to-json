package ru.sumenkov.mysqltojson.model;

import java.util.Date;

public class TableSQLModel {
    // описываем только те атрибуты, которые будем использовать (вся таблица не нужна)
    private String ptpName; // 1 column
    private int ptpId; // 2 column
    private Date dt1; // 4 column
    private String routeNum; // 7 column
    private double tarif; // 9 column
    private double prType; // 10 column, символов в этом поле не бывает, наверное лучше переделывать во float с точностью 2
    private double summ; // 11 column
    private int cnt; // 12 column
    private int qCnt; // 13 column

    public TableSQLModel() {
    }

    public TableSQLModel(String ptpName,int ptpId, Date dt1, String routeNum, double tarif, double prType, double summ,
                         int cnt, int qCnt) {
        this.ptpName = ptpName;
        this.ptpId = ptpId;
        this.dt1 = dt1;
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

    public Date getDt1() {
        return dt1;
    }

    public void setDt1(Date dt1) {
        this.dt1 = dt1;
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
