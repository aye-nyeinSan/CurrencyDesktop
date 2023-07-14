package com.example.chapter2.controller;

import com.example.chapter2.Launcher;
import com.example.chapter2.model.Currency;
import com.example.chapter2.model.CurrencyEntity;
import javafx.scene.control.TextInputDialog;

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

    public static void OnAdd(){
        try{
            TextInputDialog dialog =new TextInputDialog();
            dialog.setTitle("Add Currency");
            dialog.setContentText("Currency code: ");
            dialog.setHeaderText(null);
            dialog.setGraphic(null);
            Optional<String> code = dialog.showAndWait();
            if(code.isPresent()){
                ArrayList<Currency> currencyList =Launcher.getCurrencyList();
                Currency c= new Currency(code.get());
                ArrayList<CurrencyEntity> c_list = FetchData.fetch_range(c.getShortCode(),8);
                c.setHistorical(c_list);
                c.setCurrent(c_list.get(c_list.size() -1));
                currencyList.add(c);
                Launcher.setCurrencyList(currencyList);
                Launcher.refreshPane();
            }
        }
         catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void onDelete(String code){
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
}