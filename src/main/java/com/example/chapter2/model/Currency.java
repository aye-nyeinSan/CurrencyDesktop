package com.example.chapter2.model;

import java.util.ArrayList;

public class Currency {
    private String shortCode;
    private String baseCode;
    private CurrencyEntity current;
    private ArrayList<CurrencyEntity> historical;
    private Boolean isWatch;
    private Double watchRate;



    public Currency(String shortCode){
        this.shortCode = shortCode.toUpperCase();
       // this.baseCode = baseCode.toUpperCase();
        this.isWatch = false; //monitor
        this.watchRate = 0.0; //monitor
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public CurrencyEntity getCurrent() {
        return current;
    }

    public void setCurrent(CurrencyEntity current) {
        this.current = current;
    }

    public ArrayList<CurrencyEntity> getHistorical() {
        return historical;
    }

    public void setHistorical(ArrayList<CurrencyEntity> historical) {
        this.historical = historical;
    }

    public Boolean getWatch() {
        return isWatch;
    }

    public void setWatch(Boolean watch) {
        isWatch = watch;
    }

    public Double getWatchRate() {
        return watchRate;
    }

    public void setWatchRate(Double watchRate) {
        this.watchRate = watchRate;
    }
    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }


}
