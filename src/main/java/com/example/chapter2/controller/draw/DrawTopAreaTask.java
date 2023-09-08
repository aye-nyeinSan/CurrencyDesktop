package com.example.chapter2.controller.draw;

import com.example.chapter2.model.Currency;
import com.example.chapter2.view.TopPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Collection;
import java.util.concurrent.Callable;

public class DrawTopAreaTask   implements Callable<Pane> {
    Currency currency;
    Button watch,delete,unwatch;

    public DrawTopAreaTask(Button watch,Button unwatch,Button delete){

    this.watch=watch;
    this.unwatch=unwatch;
    this.delete =delete;

    }
    @Override
    public Pane call() throws Exception {
        HBox topArea= new HBox(10);
        topArea.setPadding(new Insets(5));

          topArea.getChildren().addAll(watch,unwatch,delete);

        topArea.setAlignment(Pos.CENTER_RIGHT);
        return topArea;
    }
}
