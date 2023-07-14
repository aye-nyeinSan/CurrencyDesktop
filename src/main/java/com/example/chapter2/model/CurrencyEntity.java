package com.example.chapter2.model;

public class CurrencyEntity {
    private String date;
    private Double rate;

    public CurrencyEntity( Double rate,String date) {
        this.date = date;
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public Double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return String.format("%s %.4f",date,rate);
    }


}
