package com.example.chapter2.controller;

import com.example.chapter2.Launcher;
import com.example.chapter2.model.Currency;
import com.example.chapter2.model.CurrencyEntity;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutionException;


public class  AllEventHandlers {
    public static void onRefresh(){
        try{
            Launcher.refreshPane();
        }
        catch(Exception e){
            e.printStackTrace();}
    }
    public static void OnBaseChange() throws ExecutionException, InterruptedException {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Base Currency");
        dialog.setContentText("Currency Code: ");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        Optional<String> baseCode = dialog.showAndWait();


        if (baseCode.isPresent()) {
            ArrayList<Currency> currencyList = Launcher.getCurrencyList();
            Currency c = new Currency();
            c.setBaseCode(baseCode.get());
            c.setShortCode("USD");


            try {
                ArrayList<CurrencyEntity> c_list = FetchData.fetch_range(c.getBaseCode(),c.getShortCode(), 30);
                c.setHistorical(c_list);
                c.setCurrent(c_list.get(c_list.size() - 1));
                currencyList.add(c);
                Launcher.setCurrencyList(currencyList);
                Launcher.refreshPane();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Invalid Currency");
                alert.setContentText("This is invalid currency code. Try again!!");
                alert.showAndWait();
                OnBaseChange();
            }

        }
    }

    public static void OnAdd() throws ExecutionException, InterruptedException {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Currency");
            dialog.setContentText("Currency Code: ");


            dialog.setHeaderText(null);
            dialog.setGraphic(null);

            //Icon
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(
                    new Image(Launcher.class.getResource("assets/cash-money-bag.png").toString()));


            Optional<String> src_code = dialog.showAndWait();
            if (src_code.isPresent()) {
                ArrayList<Currency> currencyList = Launcher.getCurrencyList();
                Currency c=new Currency();
                c.setShortCode(src_code.get());
                c.setBaseCode("THB");
                try {
                    ArrayList<CurrencyEntity> c_list = FetchData.fetch_range(c.getBaseCode(),c.getShortCode(), 30);
                    c.setHistorical(c_list);
                    c.setCurrent(c_list.get(c_list.size() - 1));
                    currencyList.add(c);
                    Launcher.setCurrencyList(currencyList);
                    Launcher.refreshPane();
                } catch (Exception e) {
                   // e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Invalid Currency");
                    alert.setContentText("This is invalid currency code. Try again!!");
                    alert.showAndWait();
                    OnAdd();
                }


            }


        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public static boolean isInValid(String text){
        return false;
    }
    public static void OnDelete(String code){
        try{
            ArrayList<Currency>currency_list =Launcher.getCurrencyList();
            int index = -1;
            for(int i=0; i < currency_list.size();i++){
                if(currency_list.get(i).getShortCode().equals(code)){
                    index=i;
                  //  currency_list.remove(i);
                    break;
                }
            }
            if(index!=-1){
                currency_list.remove(index);
                Launcher.setCurrencyList(currency_list);
                Launcher.refreshPane();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
    }
    public static void OnWatch(String code){
        try{
            ArrayList<Currency> currencyArrayList = Launcher.getCurrencyList();
            int index=-1;
            for (int i=0; i<currencyArrayList.size();i++){
                if(currencyArrayList.get(i).getShortCode().equals(code)){
                    index = i;
                    break;
                }
            }
            if (index !=-1){
                TextInputDialog dialog =new TextInputDialog();
                dialog.setTitle("Add Watch");
                dialog.setContentText("Rate: ");

                dialog.setHeaderText(null);
                dialog.setGraphic(null);
                Optional<String> retrievedRate = dialog.showAndWait();
                if(retrievedRate.isPresent()){
                    double rate =Double.parseDouble(retrievedRate.get());
                    currencyArrayList.get(index).setWatch(true);
                    currencyArrayList.get(index).setWatchRate(rate);
                    Launcher.setCurrencyList(currencyArrayList);
                    Launcher.refreshPane();
                }
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
    }

    public static void OnUnWatch(String code) throws ExecutionException, InterruptedException {

        ArrayList<Currency> currencyArrayList = Launcher.getCurrencyList();
        int index= -1;
        for (int i=0; i<currencyArrayList.size();i++){
            if(currencyArrayList.get(i).getShortCode().equals(code)){
                index = i;
                break;
            }
        }
        currencyArrayList.get(index).setWatch(false);
        currencyArrayList.get(index).setWatchRate(0.0);
        Launcher.setCurrencyList(currencyArrayList);
        Launcher.refreshPane();

    }
}
