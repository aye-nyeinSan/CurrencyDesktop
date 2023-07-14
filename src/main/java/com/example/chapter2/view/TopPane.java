package com.example.chapter2.view;

import com.example.chapter2.controller.AllEventHandlers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.time.LocalDateTime;

public class TopPane extends FlowPane {
    private Button refresh;

    private Button add;
    private Label update;
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
        add =new Button("Add");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AllEventHandlers.OnAdd();
            }
        });
        update =new Label();
        refreshPane();
        this.getChildren().addAll(refresh,add,update);
    }

    public void refreshPane() {
        update.setText((String.format("Last update : %s", LocalDateTime.now().toString())));
    }

}
