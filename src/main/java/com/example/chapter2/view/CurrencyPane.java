package com.example.chapter2.view;

import com.example.chapter2.controller.AllEventHandlers;
import com.example.chapter2.controller.draw.DrawGraphTask;
import com.example.chapter2.model.Currency;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.concurrent.*;

public class CurrencyPane extends BorderPane {
    private Currency currency;
    private Button watch;
    private Button delete;
    public CurrencyPane(Currency currency){
        this.setPadding(new Insets(0));
        this.setPrefSize(640,300);
        this.setStyle("-fx-border-color: black");
        this.watch =new Button("Watch");
        this.watch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AllEventHandlers.OnWatch(currency.getShortCode());
            }
        });
        this.delete =new Button("Delete");
        this.delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AllEventHandlers.onDelete(currency.getShortCode());
            }
        });

     try {
         this.refreshPane(currency);
     }catch(ExecutionException e){
        System.out.println("Encountered an execution exception");
     }
     catch(InterruptedException e){
         System.out.println("Encountered an interrupted exception");
     }

    }

    public void refreshPane(Currency currency) throws ExecutionException,InterruptedException {
        this.currency = currency;
        Pane currencyInfo = genInfoPane();


        FutureTask futureTask=new FutureTask<VBox>(new DrawGraphTask(currency));
        ExecutorService executorService=Executors.newSingleThreadExecutor();
        executorService.execute(futureTask);
        VBox currencyGraph = (VBox) futureTask.get();
        Pane topArea = genTopArea();

        this.setTop(topArea);
        this.setCenter(currencyGraph);
        this.setLeft(currencyInfo);


    }

    private HBox genTopArea() {
        HBox topArea= new HBox(10);
        topArea.setPadding(new Insets(5));
        topArea.getChildren().addAll(watch,delete);
        topArea.setAlignment(Pos.CENTER_RIGHT);
        return topArea;
    }

    private Pane genInfoPane() {
        VBox currencyInfoPane = new VBox(10);
        currencyInfoPane.setPadding(new Insets(5,25,5,25));
        currencyInfoPane.setAlignment(Pos.CENTER);
        Label exchangeString =new Label("");
        Label watchString =new Label("");
        //style
        exchangeString.setStyle("-fx-font-size: 20");
        watchString.setStyle("-fx-font-size: 14");
        if(this.currency != null)
        {
            exchangeString.setText(String.format("%s : %.4f",this.currency.getShortCode(),this.currency.getCurrent().getRate()));
            if(this.currency.getWatch()){
                watchString.setText(String.format("(Watch  at %.4f)",this.currency.getWatchRate() ));
            }
        }


         currencyInfoPane.getChildren().addAll(exchangeString,watchString);
         return currencyInfoPane;
    }


}
