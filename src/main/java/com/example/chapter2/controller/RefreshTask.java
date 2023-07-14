package com.example.chapter2.controller;

import com.example.chapter2.Launcher;
import com.example.chapter2.model.Currency;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class RefreshTask extends Task<Void> {
    @Override
    protected Void call() throws Exception {
        for (;;) { //repeated loop
            try {
                Thread.sleep((long) (6 * 1e3)); //minutes 6 * 10^3 >> 10^3 i s milliseconds
                System.out.println("Sleep is in " + 6 * 1e3);
            } catch (InterruptedException e) {
                System.out.print("Encounter in thread sleep.");
            }
            Platform.runLater(new Runnable() {  //Update the UI by invoking run()
                public void run() {
                    try {
                        Launcher.refreshPane();
                        // future task runs in background

                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            FutureTask futureTask = new FutureTask(new WatchTask());
            // System.out.println(futureTask);
            // java.util.concurrent.FutureTask@5e7f3097[Not completed, task = com.example.chapter2.controller.WatchTask@44a8d6c3]

            Platform.runLater(futureTask);
            boolean backgroundAlertTask = (boolean) futureTask.get();
            System.out.println("Background task: " + backgroundAlertTask);



        }
    }
}
