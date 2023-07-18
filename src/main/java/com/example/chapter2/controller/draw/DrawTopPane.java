package com.example.chapter2.controller.draw;

import com.example.chapter2.controller.AllEventHandlers;
import com.example.chapter2.model.Currency;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class DrawTopPane  implements Callable<Pane> {
 private Currency currency;
 private Button watch,delete,unwatch;
    public DrawTopPane(Currency currency){
        this.currency=currency;
    }


    @Override
    public Pane call() throws Exception {

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
                AllEventHandlers.OnDelete(currency.getShortCode());
            }
        });
        this.unwatch = new Button("UnWatch");
        this.unwatch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    AllEventHandlers.OnUnWatch(currency.getShortCode());
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        HBox topArea= new HBox(10);
        topArea.setPadding(new Insets(5));
        topArea.getChildren().addAll(watch,unwatch,delete);
        topArea.setAlignment(Pos.CENTER_RIGHT);


        return topArea;
    }
}


