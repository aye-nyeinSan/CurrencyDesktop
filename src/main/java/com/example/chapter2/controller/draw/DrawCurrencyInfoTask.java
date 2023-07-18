package com.example.chapter2.controller.draw;

import com.example.chapter2.model.Currency;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.concurrent.Callable;

public class DrawCurrencyInfoTask implements Callable<VBox> {
    private Currency currency;
    public DrawCurrencyInfoTask(Currency currency) {
        this.currency = currency;
    }

    @Override
    public VBox call() throws Exception {
        VBox currencyInfoPane = new VBox(10);
        currencyInfoPane.setPadding(new Insets(5,25,5,25));
        currencyInfoPane.setAlignment(Pos.CENTER);
        Label exchangeString =new Label("");
        Label watchString =new Label("");
        Label baseString = new Label("");
        //style
        exchangeString.setStyle("-fx-font-size: 20");
        watchString.setStyle("-fx-font-size: 14");
        baseString.setStyle("-fx-font-size: 14");
        if(this.currency != null)
        {
            baseString.setText(String.format("Base currency : %s ",this.currency.getBaseCode()));
            exchangeString.setText(String.format("%s : %.4f",this.currency.getShortCode(),this.currency.getCurrent().getRate()));
            if(this.currency.getWatch()){
                watchString.setText(String.format("(Watch at %.4f)",this.currency.getWatchRate() ));
            }
        }


        currencyInfoPane.getChildren().addAll(baseString,exchangeString,watchString);
        return currencyInfoPane;
    }
}
