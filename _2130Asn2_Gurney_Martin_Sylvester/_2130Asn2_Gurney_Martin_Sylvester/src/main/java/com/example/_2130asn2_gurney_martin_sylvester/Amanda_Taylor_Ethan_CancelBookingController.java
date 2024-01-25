package com.example._2130asn2_gurney_martin_sylvester;

// Authors:      Amanda Gurney, Taylor Martin, Ethan Sylvester
// Student IDs:  101443253,     100849882,     101479568
// Course Code:  COMP2130
// Group Number: 13

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.List;
import java.util.stream.Collectors;

public class Amanda_Taylor_Ethan_CancelBookingController {
    @FXML
    Button cancelBookingButton;
    @FXML
    TextField passportIDField;
    @FXML
    Label cancelReviewLabel, cancelPassNumLabel;
    @FXML
    private ListView<Amanda_Taylor_Ethan_Passenger> passengerListView;
    static List<Amanda_Taylor_Ethan_Passenger> amandaTaylorEthanPassengers = Amanda_Taylor_Ethan_Passenger.loadPassengersFromFile("passengers.txt");

    // Initialize methods
    // Filter passengers with true bookings
    // Display the filtered list in the listview
    // Add event handler to the cancelBookingButton
    public void initialize() {
        List<Amanda_Taylor_Ethan_Passenger> passList = amandaTaylorEthanPassengers.stream()
                .filter(passenger -> passenger.hasBooked)
                .collect(Collectors.toList());

        passengerListView.getItems().addAll(passList);
        cancelBookingButton.setOnAction(event -> cancelBooking(amandaTaylorEthanPassengers));

        labelDesign(cancelReviewLabel,  "Review Passengers for Booking Cancellation:", 125.0);
        labelDesign(cancelPassNumLabel, "Enter Passenger Passport Number:",            475.0);
    }

    // METHODS
    // Method to for pop up alert to inform user about their input
    private void showValidationConfirmationAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Get the entered passport id from text field
    // Find the passenger with the matching passport ID
    // Update the booking status if the passenger is found
    // Save changes back to the file
    // Refresh the listview display
    // Validation for correct and incorrect entries
    private void cancelBooking(List<Amanda_Taylor_Ethan_Passenger> amandaTaylorEthanPassengers) {
        String passportID = passportIDField.getText().trim();

        Amanda_Taylor_Ethan_Passenger amandaTaylorEthanPassengerToCancel = amandaTaylorEthanPassengers.stream()
                .filter(passenger -> passenger.getPassportID().equals(passportID))
                .findFirst()
                .orElse(null);

        if (amandaTaylorEthanPassengerToCancel != null) {
            amandaTaylorEthanPassengerToCancel.setHasBooked(false);

            Amanda_Taylor_Ethan_Passenger.savePassengersToFile(amandaTaylorEthanPassengers, "passengers.txt");

            passengerListView.getItems().clear();
            passengerListView.getItems().addAll(amandaTaylorEthanPassengers.stream()
                    .filter(passenger -> passenger.hasBooked)
                    .collect(Collectors.toList()));

            showValidationConfirmationAlert("Booking Cancelled", "Cancelled Booking for Passenger with Passport ID: " + passportID);
        } else {
            showValidationConfirmationAlert("Error", "Could not Find Passenger with Passport ID: " + passportID);
        }
        passportIDField.clear();
    }

    // UI METHODS
    // Designs for labels on pass booking cancellation page
    private void labelDesign(Label label, String text, double layoutY){
        label.setText(text);
        label.setLayoutX(675.0);
        label.setLayoutY(layoutY);

        label.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold;");
    }
}