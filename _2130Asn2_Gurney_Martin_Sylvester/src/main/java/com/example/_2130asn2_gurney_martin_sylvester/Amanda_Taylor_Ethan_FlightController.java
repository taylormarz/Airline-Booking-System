package com.example._2130asn2_gurney_martin_sylvester;

// Authors:      Amanda Gurney, Taylor Martin, Ethan Sylvester
// Student IDs:  101443253,     100849882,     101479568
// Course Code:  COMP2130
// Group Number: 13

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.util.List;
import javafx.scene.control.DatePicker;

public class Amanda_Taylor_Ethan_FlightController {

    // VARIABLES
    @FXML
    private TextField flightNumberField, flightSourceField, flightDestinationField, flightMaxPassField;
    @FXML
    private DatePicker flightDateField;
    @FXML
    private Label enterFlightLabel, enterDateLabel, enterSourceLabel, enterDestinationLabel, enterMaxPassLabel;
    static private List<Amanda_Taylor_Ethan_Flight> amandaTaylorEthanFlights = Amanda_Taylor_Ethan_Flight.loadFlightsFromFile("flights.txt");
    @FXML
    private void initialize() {
        labelDesign(enterFlightLabel,      "Enter 4-Digit Flight Number:",     125.0);
        labelDesign(enterDateLabel,        "Enter Flight Date:",               215.0);
        labelDesign(enterSourceLabel,      "Enter Flight Source:",             305.0);
        labelDesign(enterDestinationLabel, "Enter Flight Destination:",        395.0);
        labelDesign(enterMaxPassLabel,     "Enter Max Passengers for Flight:", 485.0);

        textFieldDesign(flightNumberField, "Flight Number",    150.0);
        textFieldDesign(flightSourceField, "Source",           330.0);
        textFieldDesign(flightDestinationField, "Destination", 420.0);
        textFieldDesign(flightMaxPassField, "Max Passengers",  510.0);
    }

    // ACCESSOR
    static public List<Amanda_Taylor_Ethan_Flight> getFlights() {
        return amandaTaylorEthanFlights;
    }

    // METHODS
    // Alert method for validation in controller
    private void showValidationErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Helper method to check if a name is valid
    private boolean isValidName(String name) {
        // Use the provided regular expression to ensure the name follows the specified pattern
        return name.matches("^[A-Za-z]+(['\\s-][A-Za-z]+)*$");
    }

    @FXML
    private void onAddFlightSubmit() {
        try {
            // Get input from text fields
            String flightNumber = flightNumberField.getText();
            LocalDate flightDate = flightDateField.getValue();
            String source = flightSourceField.getText();
            String destination = flightDestinationField.getText();
            int maxPassenger = Integer.parseInt(flightMaxPassField.getText());

            // VALIDATION
            // Check that inputs aren't left empty
            // Alert pop up if invalid
            if (flightNumber.isEmpty() || flightDate == null || source.isEmpty() || destination.isEmpty()) {
                showValidationErrorAlert("Invalid Input", "Please fill in all required fields.");
                return;
            }

            // Check that flight number is 4 digits
            // Alert pop up if invalid
            if (flightNumber.length() != 4 || !flightNumber.matches("\\d+")) {
                showValidationErrorAlert("Invalid Flight Number", "Flight number must be 4 digits.");
                return;
            }

            // Check that flight date is not a past date
            // Alert pop up if invalid
            if (flightDate.isBefore(LocalDate.now())) {
                showValidationErrorAlert("Invalid Flight Date", "Flight date cannot be in the past.");
                return;
            }

            // Check that source and destination are not numbers or special characters
            // Alert pop up if invalid
            if (!isValidName(source) || !isValidName(destination)) {
                showValidationErrorAlert("Invalid Source/Destination", "Source and destination can't have numbers or special characters.");
                return;
            }

            // Check that maxPassengers isn't 0 or a negative number (or non-numeric characters)
            // Alert pop up if invalid
            try {
                if (maxPassenger <= 0) {
                    showValidationErrorAlert("Invalid Max Passengers", "Max passengers must be greater than zero.");
                    return;
                }
            } catch (NumberFormatException e) {
                showValidationErrorAlert("Invalid Max Passengers", "Max passengers must be a valid numeric value.");
                return;
            }

            // Create a new Flight object
            // Save the new flight to txt file
            Amanda_Taylor_Ethan_Flight newAmandaTaylorEthanFlight = new Amanda_Taylor_Ethan_Flight(flightNumber, flightDate, source, destination, maxPassenger);
            amandaTaylorEthanFlights.add(newAmandaTaylorEthanFlight);
            Amanda_Taylor_Ethan_Flight.saveFlightsToFile(amandaTaylorEthanFlights, "flights.txt");


            // Display pop up to tell user flight added successfully
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Flight Added");
            alert.setHeaderText(null);
            alert.setContentText("New Flight Added: " + newAmandaTaylorEthanFlight);
            alert.showAndWait();

            // Clear user inputs after flight is submitted
            flightNumberField.clear();
            flightDateField.setValue(null);
            flightSourceField.clear();
            flightDestinationField.clear();
            flightMaxPassField.clear();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error adding flight. Please check your input.");
            alert.showAndWait();
        }
    }

    // UI METHODS
    // Designs for labels on add flight page
    private void labelDesign(Label label, String text, double layoutY){
        label.setText(text);
        label.setLayoutX(675.0);
        label.setLayoutY(layoutY);

        label.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
    }

    // Designs for text fields on add flight page
    private void textFieldDesign(TextField textField, String promptText, double layoutY){
        textField.setPromptText(promptText);
        textField.setLayoutY(layoutY);

        textField.setLayoutX(675.0);
        textField.setPrefWidth(300.0);
        textField.setPrefHeight(40.0);
    }
}