package com.example._2130asn2_gurney_martin_sylvester;

// Authors:      Amanda Gurney, Taylor Martin, Ethan Sylvester
// Student IDs:  101443253,     100849882,     101479568
// Course Code:  COMP2130
// Group Number: 13

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Amanda_Taylor_Ethan_NewPassController {
    // VARIABLES
    @FXML
    private TextField passportIDField, firstNameField, lastNameField, ageField, flightFareField;
    @FXML
    private Button yesBookingButton, noBookingButton, addNewPassButton;
    @FXML
    private Label passportNumLabel, fnameLabel, lnameLabel, passAgeLabel, flightFareLabel, confirmedLabel;
    private List<Amanda_Taylor_Ethan_Passenger> amandaTaylorEthanPassengers = new ArrayList<>();
    private boolean hasBooked;

    // Set event handlers for the Yes and No buttons
    // Set event handler for the addNewPassButton
    // Initialize design methods for labels and text fields
    @FXML
    private void initialize() {
        yesBookingButton.setOnAction(event -> handleYesBookingButton());
        noBookingButton.setOnAction(event -> handleNoBookingButton());
        addNewPassButton.setOnAction(event -> onAddNewPassSubmit());

        labelDesign(passportNumLabel, "Enter a 4-Digit Passport Number:",           125.0);
        labelDesign(fnameLabel,       "Enter Passenger First Name:",                215.0);
        labelDesign(lnameLabel,       "Enter Passenger Last Name:",                 305.0);
        labelDesign(passAgeLabel,     "Enter Passenger Age:",                       395.0);
        labelDesign(flightFareLabel,  "Enter Flight Fare:",                         485.0);
        labelDesign(confirmedLabel,   "Has the Passenger Confirmed their Booking?", 575.0);

        textFieldDesign(passportIDField, "Passport Number", 150.0);
        textFieldDesign(firstNameField,  "First Name",      240.0);
        textFieldDesign(lastNameField,   "Last Name",       330.0);
        textFieldDesign(ageField,        "Age",             420.0);
        textFieldDesign(flightFareField, "Flight Fare",     510.0);
    }

    @FXML
    private void handleYesBookingButton() {
        hasBooked = true;
    }
    @FXML
    private void handleNoBookingButton() {
        hasBooked = false;
    }

    // METHODS
    // Method to check if name user inputs is valid
    private boolean isValidName(String name) {
        // Use the provided regular expression to ensure the name follows the specified pattern
        return name.matches("^[A-Za-z]+(['\\s-][A-Za-z]+)*$");
    }

    // Method to show validation error alert
    private void showValidationErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Method to check if a string is numeric
    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    // Get user input from text fields when submit button is clicked
    @FXML
    private void onAddNewPassSubmit() {
        try {
            String passportID = passportIDField.getText();
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String age = ageField.getText();
            String flightFare = flightFareField.getText();
            LocalDate date = LocalDate.now();

            // VALIDATION
            // Show an error alert if any of the required fields are empty or if the names are invalid
            if (passportID.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || age.isEmpty() || flightFare.isEmpty()) {
                showValidationErrorAlert("Invalid Input", "Fields cannot be left empty.");
                return;
            }

            // Validate passportID length
            // Show an error alert if passportID is longer than 4 digits
            if (passportID.length() != 4) {
                showValidationErrorAlert("Invalid Passport ID", "Passport ID must be 4-digits.");
                return;
            }

            // Use valid name method, if names are not valid alert box pop up telling user
            if (!isValidName(firstName) || (!isValidName(lastName))) {
                showValidationErrorAlert("Invalid Input", "Names cannot contain special characters or numbers. " +
                        "Using ' - ' or ' ' ' is accepted if accompanied by letters.");
                return;
            }

            // Validate numeric fields (age, flightFare)
            // Show an error alert if age or flightFare is not a valid number
            if (!isNumeric(age) || !isNumeric(flightFare) || !age.matches("\\d+") || !flightFare.matches("\\d+")) {
                showValidationErrorAlert("Invalid Input", "Age and Flight Fare must be positive numeric values.");
                return;
            }

            // Check if age is more than 3 digits
            if (age.length() > 3) {
                showValidationErrorAlert("Invalid Age", "Age cannot be more than 3 digits.");
                return;
            }

            // Create new Passenger object
            Amanda_Taylor_Ethan_Passenger newAmandaTaylorEthanPassenger = new Amanda_Taylor_Ethan_Passenger(passportID, firstName, lastName, age, flightFare, hasBooked, date);

            // Save new Passenger to txt file
            List<Amanda_Taylor_Ethan_Passenger> amandaTaylorEthanPassengers = Amanda_Taylor_Ethan_Passenger.loadPassengersFromFile("passengers.txt");
            amandaTaylorEthanPassengers.add(newAmandaTaylorEthanPassenger);
            Amanda_Taylor_Ethan_Passenger.savePassengersToFile(amandaTaylorEthanPassengers, "passengers.txt");

            // Pop up to tell user passenger added successfully
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("New Passenger Added");
            alert.setHeaderText(null);
            alert.setContentText("New Passenger Added: " + newAmandaTaylorEthanPassenger);
            alert.showAndWait();

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error adding passenger. Please check your inputs.");
            alert.showAndWait();
        }

        // Clear user inputs after passenger is submitted
        passportIDField.clear();
        firstNameField.clear();
        lastNameField.clear();
        ageField.clear();
        flightFareField.clear();
    }

    // UI METHODS
    // Designs for labels on add passenger to system page
    private void labelDesign(Label label, String text, double layoutY){
        label.setText(text);
        label.setLayoutX(675.0);
        label.setLayoutY(layoutY);

        label.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
    }

    // Designs for text fields on add passenger to system page
    private void textFieldDesign(TextField textField, String promptText, double layoutY){
        textField.setPromptText(promptText);
        textField.setLayoutY(layoutY);

        textField.setLayoutX(675.0);
        textField.setPrefWidth(300.0);
        textField.setPrefHeight(40.0);
    }
}