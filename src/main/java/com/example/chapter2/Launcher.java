package com.example.chapter2;

import com.example.chapter2.controller.Initialize;
import com.example.chapter2.controller.RefreshTask;
import com.example.chapter2.model.Currency;
import com.example.chapter2.view.CurrencyParentPane;
import com.example.chapter2.view.TopPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Launcher extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    public static Stage primaryStage;
    private static Scene mainScene;
    private static FlowPane mainPane;
    private static TopPane topPane;
   // private static CurrencyPane currencyPane;
    private static CurrencyParentPane currencyParentPane;

    // private static Currency currency;
    public static ArrayList<Currency> currencyList;




    @Override
    public void start(Stage stage) throws ExecutionException, InterruptedException, IOException {
    primaryStage =stage;
    primaryStage.setTitle("Currency Watcher");
    primaryStage.getIcons().add(new Image(Launcher.class.getResource("assets/cash-money-bag.png").toString()));
     this.primaryStage.setResizable(false);
     //System.out.println(FetchData.fetch_range("USD",6));
        //this.currency = Initialize.initialize_app();
        currencyList = Initialize.initialize_app();

        initMainPane();
        mainScene = new Scene(mainPane);
        primaryStage.setScene(mainScene);
        primaryStage.show();

       RefreshTask r =new RefreshTask();
        Thread th= new Thread(r);
        th.setDaemon(true);
        th.start();
    }

    private void initMainPane() throws ExecutionException, InterruptedException {
        mainPane = new FlowPane();
        topPane = new TopPane();

       //  currencyPane = new CurrencyPane(this.currency);
        currencyParentPane = new CurrencyParentPane(currencyList);
        mainPane.getChildren().add(topPane);
       // mainPane.getChildren().add(currencyPane);
        mainPane.getChildren().add(currencyParentPane);

    }
    public static void refreshPane() throws InterruptedException, ExecutionException{
        topPane.refreshPane();
      //  currencyPane.refreshPane(currency);
        currencyParentPane.refreshPane(currencyList);
        primaryStage.sizeToScene();

    }
    public static ArrayList<Currency> getCurrencyList() {
        return currencyList;
    }
    public static void setCurrencyList(ArrayList<Currency> currencyList) {
        Launcher.currencyList = currencyList;
    }
}
