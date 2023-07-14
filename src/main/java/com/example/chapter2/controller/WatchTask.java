package com.example.chapter2.controller;

import com.example.chapter2.Launcher;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import com.example.chapter2.model.Currency;
import javafx.scene.control.Alert;

public class  WatchTask implements Callable<Boolean>
{
    @Override
    public Boolean call() throws Exception {
        ArrayList<Currency> allCurrency = Launcher.getCurrencyList();
        String found = "";
        for (Currency currency : allCurrency) {
            if (currency.getWatchRate() != 0 && currency.getWatchRate() > currency.getCurrent().getRate()) {
                if (found.equals("")) {
                    found = currency.getShortCode();
                } else {
                    found = found + " and " + currency.getShortCode();
                }
            }
        }
       // System.out.println(found); //shortcode
        if(!found.equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Watch Alert");
            alert.setHeaderText(null);
            if(found.length()>3){
                alert.setContentText(String.format("%s have become lower than the watch rate! ",found));
            }else {
                alert.setContentText(String.format("%s has become lower than the watch rates! ",found));
            }
            alert.showAndWait();
            return true;

        }
        return false;
    }
}
