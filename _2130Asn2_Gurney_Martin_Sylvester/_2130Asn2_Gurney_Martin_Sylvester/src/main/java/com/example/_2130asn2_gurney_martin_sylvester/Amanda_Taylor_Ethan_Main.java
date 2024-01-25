package com.example._2130asn2_gurney_martin_sylvester;

// Authors:      Amanda Gurney, Taylor Martin, Ethan Sylvester
// Student IDs:  101443253,     100849882,     101479568
// Course Code:  COMP2130
// Group Number: 13

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Amanda_Taylor_Ethan_Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Amanda_Taylor_Ethan_Main.class.getResource("main-view.fxml"));

        // Creating scene and setting it to be 1250x800
        Scene scene = new Scene(fxmlLoader.load(), 1250, 800);
        stage.setTitle("GBC Airline Booking System");
        stage.setScene(scene);

        // Setting ability to resize scene to false so our user can't break the interface
        stage.setResizable(false);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}