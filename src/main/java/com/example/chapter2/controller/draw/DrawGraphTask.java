package com.example.chapter2.controller.draw;

import com.example.chapter2.model.Currency;
import com.example.chapter2.model.CurrencyEntity;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

import java.util.concurrent.Callable;

public class DrawGraphTask implements Callable<VBox> {
    Currency currency;

    public DrawGraphTask(Currency currency){
        this.currency=currency;
    }
    public VBox call() throws Exception{
        VBox graphPane =new VBox(10);
        graphPane.setPadding(new Insets(0,25,5,25));
        //chart
        CategoryAxis xAxis= new CategoryAxis();//Category
        NumberAxis yAxis = new NumberAxis(); //Number
        yAxis.setAutoRanging(true);
        LineChart lineChart= new LineChart<>(xAxis,yAxis);
        lineChart.setLegendVisible(false);
        if(this.currency != null){
            XYChart.Series series =new XYChart.Series();
            double min_y =Double.MAX_VALUE;
            double max_y = Double.MIN_VALUE;
            for (CurrencyEntity c: currency.getHistorical()){
                series.getData().add(new XYChart.Data(c.getDate(),c.getRate()));
                if(c.getRate()> max_y) max_y=c.getRate();
                if (c.getRate()<min_y)  min_y=c.getRate();
            }

            yAxis.setAutoRanging(false);
            yAxis.setLowerBound(min_y - (max_y -min_y)/2);
            yAxis.setUpperBound(max_y + (max_y -min_y)/2);
            yAxis. setTickUnit((max_y - min_y)/2);

            lineChart.getData().add(series);

        }

        graphPane.getChildren().add(lineChart);
        return graphPane;

    }
}
