package com.example.chapter2.controller;

import com.example.chapter2.model.Currency;
import com.example.chapter2.model.CurrencyEntity;

import java.io.IOException;
import java.util.ArrayList;

public class Initialize {
    public static ArrayList<Currency>  initialize_app() throws IOException {
        Currency c = new Currency("THB","USD");
        ArrayList<CurrencyEntity> c_list = FetchData.fetch_range(c.getBaseCode(),c.getShortCode(),30);
        c.setHistorical(c_list);
        c.setCurrent(c_list.get(c_list.size()-1));

        ArrayList <Currency> currencyList =new ArrayList<>();
        currencyList.add(c);
        return currencyList;

    }
}
