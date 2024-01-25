package com.example._2130asn2_gurney_martin_sylvester;

// Authors:      Amanda Gurney, Taylor Martin, Ethan Sylvester
// Student IDs:  101443253,     100849882,     101479568
// Course Code:  COMP2130
// Group Number: 13

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class Amanda_Taylor_Ethan_PrintManifestController {
    @FXML
    private ListView<Amanda_Taylor_Ethan_Flight> flightListView;
    List<Amanda_Taylor_Ethan_Flight> amandaTaylorEthanFlights = Amanda_Taylor_Ethan_FlightController.getFlights();
    List<Amanda_Taylor_Ethan_Passenger> amandaTaylorEthanPassengers = Amanda_Taylor_Ethan_PassBookingController.getPassengers();
    @FXML
    private TextField flightIDField;
    @FXML
    private ListView<Amanda_Taylor_Ethan_Passenger> manifestListView;
    @FXML Label reviewFlightsLabel, enterFlightNumLabel, manifestLabel;

    @FXML
    private void initialize () {
        flightListView.getItems().addAll(amandaTaylorEthanFlights);

        labelDesign(reviewFlightsLabel,  "Review Available Flights for Manifest Printing:",       125.0);
        labelDesign(enterFlightNumLabel, "Enter Flight Number of Manifest You'd Like to Print:",  325.0);
        labelDesign(manifestLabel,       "Flight Manifest of Select Flight:",                     500.0);
    }

    // METHODS
    private void showNoMatchingFlightAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Flight Not Found");
        alert.setHeaderText(null);
        alert.setContentText("No matching flight number found. Please try again.");

        alert.showAndWait();
    }
    private void showNoPassengersAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Passengers");
        alert.setHeaderText(null);
        alert.setContentText("No passengers added to this flight manifest yet.");
        alert.showAndWait();
    }

    // Method to print passenger manifest of specified flight number
    // If flight number doesn't exist, alerts user and lets them try new number
    // If flight doesn't have passengers in manifest yet, alert user to let them know
    @FXML
    private void printToManifest() {
        manifestListView.getItems().clear();

        String flightNumber = flightIDField.getText();
        List<Amanda_Taylor_Ethan_Passenger> occupiedSeats = new ArrayList<>();

        for (Amanda_Taylor_Ethan_Flight amandaTaylorEthanFlight : amandaTaylorEthanFlights) {
            if (amandaTaylorEthanFlight.flightNumber.equals(flightNumber)) {
                for (Amanda_Taylor_Ethan_Passenger pass : amandaTaylorEthanFlight.getPassengers()) {
                    if (pass != null) {
                        occupiedSeats.add(pass);
                    }
                }
                if (occupiedSeats.isEmpty()) {
                    showNoPassengersAlert();
                } else {
                    manifestListView.getItems().addAll(occupiedSeats);
                }
                return;
            }
        }
        showNoMatchingFlightAlert();
    }

    // UI METHODS
    // Designs for labels on print flight manifest page
    private void labelDesign(Label label, String text, double layoutY){
        label.setText(text);
        label.setLayoutX(675.0);
        label.setLayoutY(layoutY);

        label.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
    }
}