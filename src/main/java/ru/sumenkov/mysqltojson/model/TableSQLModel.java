package ru.sumenkov.mysqltojson.model;

import java.util.Date;

public class TableSQLModel {
    // описываем только те атрибуты, которые будем использовать (вся таблица не нужна)
    private String ptpName;
    private int ptpId;
    private Date dt1;
    private String routeNum;
    private float tarif;
    private String prType; // символов в этом полене бывает, наверное лучше переделывать во float с точностью 2
    private float summ;
    private int cnt;
    private int qCnt;

    public TableSQLModel() {
    }

    public TableSQLModel(String ptpName,int ptpId, Date dt1, String routeNum, float tarif, String prType, float summ,
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

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public String getPrType() {
        return prType;
    }

    public void setPrType(String prType) {
        this.prType = prType;
    }

    public float getSumm() {
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
