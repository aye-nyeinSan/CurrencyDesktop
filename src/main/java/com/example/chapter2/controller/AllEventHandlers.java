package com.example.chapter2.controller;

import com.example.chapter2.Launcher;
import com.example.chapter2.model.Currency;
import com.example.chapter2.model.CurrencyEntity;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
    public static void OnBaseChange(Currency currency) throws ExecutionException, InterruptedException {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Base Currency");
        dialog.setContentText("Currency Code: ");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        //Icon
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(
                new Image(Launcher.class.getResource("assets/cash-money-bag.png").toString()));

        Optional<String> baseCode = dialog.showAndWait();


        if (baseCode.isPresent()) {
            ArrayList<Currency> currencyList = Launcher.getCurrencyList();
            Currency c = new Currency();
            c.setBaseCode(baseCode.get().toUpperCase());
            c.setShortCode("USD");
            if(isCurrencyValid(c.getBaseCode().toUpperCase())){
                OnAdd(c);
                Launcher.refreshPane();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Invalid Currency");
                alert.setContentText("This is invalid base currency code. Try again!!");
               alert.showAndWait();
                OnBaseChange(currency);
            }


        }
    }

    public static void OnAdd(Currency currency) throws ExecutionException, InterruptedException {

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
              //  System.out.println(currencyList.lastIndexOf(currency));
               // String baseCode = "THB";
                Currency c = new Currency(currency.getBaseCode(), src_code.get().toUpperCase());

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
                    OnAdd(currency);
                }


            }


        }


    public static boolean isCurrencyValid(String code){
        String api= "https://api.exchangerate.host/latest";
        try {
            URL url = new URL(api);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String apiResponse = response.toString();

            // Check if the currency code exists in the API response
            return apiResponse.contains("\"" + code + "\"");

        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception and return false or perform alternative error handling
            return false;
    }
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
