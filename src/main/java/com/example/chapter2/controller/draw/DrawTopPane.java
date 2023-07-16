package com.example.chapter2.controller.draw;

import com.example.chapter2.controller.AllEventHandlers;
import com.example.chapter2.model.Currency;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class DrawTopPane extends FlowPane implements Callable<FlowPane> {
 private Currency currency;
 private Button watch,delete,unwatch;
    public DrawTopPane(Currency currency){
        this.currency=currency;
    }
    @Override
    public FlowPane call() throws Exception {
    FlowPane toppane= new FlowPane();
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setPrefSize(640, 20);
        this.setStyle("-fx-border-color: black");
        this.watch = new Button("Watch");
        this.watch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AllEventHandlers.OnWatch(currency.getShortCode());
            }
        });
        this.delete = new Button("Delete");
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
        toppane.getChildren().addAll(watch,unwatch,delete);
        return toppane ;
    }
}


