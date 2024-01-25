package com.example._2130asn2_gurney_martin_sylvester;

// Authors:      Amanda Gurney, Taylor Martin, Ethan Sylvester
// Student IDs:  101443253,     100849882,     101479568
// Course Code:  COMP2130
// Group Number: 13

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.util.List;

public class Amanda_Taylor_Ethan_PassManifestController {
    // VARIABLES
    @FXML
    private ListView<Amanda_Taylor_Ethan_Flight> flightListView;
    List<Amanda_Taylor_Ethan_Flight> amandaTaylorEthanFlights = Amanda_Taylor_Ethan_FlightController.getFlights();
    List<Amanda_Taylor_Ethan_Passenger> amandaTaylorEthanPassengers = Amanda_Taylor_Ethan_PassBookingController.getPassengers();
    @FXML
    private TextField passportIDField;
    @FXML
    private TextField flightNumberField;
    @FXML
    private void initialize() {
        flightListView.getItems().addAll(amandaTaylorEthanFlights);
    }

    // METHOD
    // Adding passengers to a specific flights manifest
    // Take values from text boxes
    // Assign a null value to passenger to check against later
    // Checking for a passenger who exists
    // Checking if a flight matches the number
    @FXML
    private void addToManifestButton() {
        String passportID = passportIDField.getText();
        String flightNumber = flightNumberField.getText();

        Amanda_Taylor_Ethan_Passenger amandaTaylorEthanPassenger = null;
        boolean passportIDMatch = false;

        for (Amanda_Taylor_Ethan_Passenger pass : amandaTaylorEthanPassengers) {
            if (pass.passportID.equals(passportID)) {
                amandaTaylorEthanPassenger = pass;
                passportIDMatch = true;
                break;
            }
        }

        if (passportIDMatch) {
            boolean flightNumberMatch = false;
            for (Amanda_Taylor_Ethan_Flight amandaTaylorEthanFlight : amandaTaylorEthanFlights) {
                if (amandaTaylorEthanFlight.flightNumber.equals(flightNumber)) {
                    flightNumberMatch = true;
                    amandaTaylorEthanFlight.addPassenger(amandaTaylorEthanPassenger);
                    System.out.println("PASSENGER " + amandaTaylorEthanPassenger.firstName + " ADDED TO FLIGHT " + amandaTaylorEthanFlight.flightNumber);
                    break;
                }
            }

            if (!flightNumberMatch) {
                showAlert("Flight Number not found", "Please enter a valid flight number.");
            }
        } else {
            showAlert("Passport ID not found", "Please enter a valid passport ID.");
        }
    }

    // VALIDATION
    // Method to create alert pop up in case user input is incorrect
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}