package com.example.chapter2.view;

import com.example.chapter2.controller.AllEventHandlers;
import com.example.chapter2.model.Currency;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

public class TopPane extends FlowPane {
    private Currency currency;
    private Button refresh;
    private Button base;
    private Button add;
    private Label update;
    private Label baseCurrency;
    public TopPane(){
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setPrefSize(640,20);

        refresh = new Button ("Refresh");
        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AllEventHandlers.onRefresh();
            }
        });
        base =new Button("Change Base");
        base.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    AllEventHandlers.OnBaseChange();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        add =new Button("Add");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    AllEventHandlers.OnAdd();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        update =new Label();
        baseCurrency = new Label();
        refreshPane();
        this.getChildren().addAll(refresh,base,add,baseCurrency,update);
    }

    public void refreshPane() {
        //baseCurrency.setText(String.format("Base Currency : %s", this.currency.getBaseCode().toString()));
        update.setText((String.format("Last update : %s", LocalDateTime.now().toString())));
    }

}
