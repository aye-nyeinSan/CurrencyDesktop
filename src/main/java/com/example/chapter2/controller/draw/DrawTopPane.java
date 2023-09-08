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

    public DrawTopPane(Currency currency){
        this.currency=currency;
    }


    @Override
    public Pane call() throws Exception {

       return null;
    }
}


