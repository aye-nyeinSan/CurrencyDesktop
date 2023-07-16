package com.example.chapter2.controller.draw;

import com.example.chapter2.model.Currency;
import com.example.chapter2.view.TopPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.util.concurrent.Callable;

public class DrawTopAreaTask implements Callable<HBox> {
    Currency currency;

    public DrawTopAreaTask(Currency currency){
        this.currency=currency;
    }
    @Override
    public HBox call() throws Exception {
        HBox topArea= new HBox(10);
        topArea.setPadding(new Insets(5));

        topArea.getChildren().addAll(new DrawTopPane(currency));
        topArea.setAlignment(Pos.CENTER_RIGHT);
        return topArea;
    }
}
