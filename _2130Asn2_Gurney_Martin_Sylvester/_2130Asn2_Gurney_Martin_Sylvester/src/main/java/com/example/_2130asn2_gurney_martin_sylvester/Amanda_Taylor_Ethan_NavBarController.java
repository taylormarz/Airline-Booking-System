package com.example._2130asn2_gurney_martin_sylvester;

// Authors:      Amanda Gurney, Taylor Martin, Ethan Sylvester
// Student IDs:  101443253,     100849882,     101479568
// Course Code:  COMP2130
// Group Number: 13

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;

public class Amanda_Taylor_Ethan_NavBarController {
    // VARIABLES
    @FXML
    private Button homeButton, addFlightButton, addNewPassButton, addPassToManiButton,
            passBookingConfirmButton, cancelBookingConfirmButton, printManifestButton, last7DaysBookingButton;

    // NAVIGATION METHODS
    // Method for navigating to new scenes via navbar button clicks
    private void onNavButtonClick(String fxmlFileName, Button buttonId) {
        try {
            // Creating new FXMLLoader
            // Load fxml fle
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Pane root = fxmlLoader.load();

            // Create new scene, dimensions of scene set to 1250x800
            // Get current stage (reference)
            // Set new scene on current stage
            Scene scene = new Scene(root, 1250, 800);
            Stage stage = (Stage) buttonId.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            // Exception handler during loading
            e.printStackTrace();
        }
    }

    // Navigate to home page
    @FXML
    private void onHomeButtonClick() {
        onNavButtonClick("main-view.fxml", homeButton);
    }
    // Navigate to add flight

    @FXML
    private void onAddFlightButtonClick() {
        onNavButtonClick("flight-view.fxml", addFlightButton);
    }

    // Navigate to add new passenger page
    @FXML
    private void onAddNewPassButtonClick() {
        onNavButtonClick("newpass-view.fxml", addNewPassButton);
    }

    // Navigate to add passenger to manifest page
    @FXML
    private void onAddPassToManiButtonClick() {
        onNavButtonClick("passmanifest-view.fxml", addPassToManiButton);
    }

    // Navigate to booking confirmation page
    @FXML
    private void onBookingConfirmButtonClick() {
        onNavButtonClick("passbooking-view.fxml", passBookingConfirmButton);
    }

    // Navigate to cancel bookings page
    @FXML
    private void onCancelButtonClick() {
        onNavButtonClick("cancelbooking-view.fxml", cancelBookingConfirmButton);
    }

    // Navigate to print manifest page
    @FXML
    private void onPrintManiButtonClick() {
        onNavButtonClick("printmanifest-view.fxml", printManifestButton);
    }

    // Navigate to booking from last 7 days page
    @FXML
    private void onLast7ButtonClick() {
        onNavButtonClick("viewbookings-view.fxml", last7DaysBookingButton);
    }

    // UI METHODS
    // INITIALIZATION FOR METHODS
    @FXML
    private void initialize() {
        // Initialize hover events for buttons
        navButtonHoverEvent(homeButton);
        navButtonHoverEvent(addFlightButton);
        navButtonHoverEvent(addNewPassButton);
        navButtonHoverEvent(addPassToManiButton);
        navButtonHoverEvent(passBookingConfirmButton);
        navButtonHoverEvent(cancelBookingConfirmButton);
        navButtonHoverEvent(printManifestButton);
        navButtonHoverEvent(last7DaysBookingButton);
    }

    // Method for button hover on navbar
    private void navButtonHoverEvent(Button button) {
        String originalStyle = button.getStyle();

        // Apply hover background color for nav bar buttons
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> button.setStyle(originalStyle + "-fx-background-color: #0c5061;"));

        // Buttons go back to original colour when mouse isn't hovering anymore
        button.addEventHandler(MouseEvent.MOUSE_EXITED, event -> button.setStyle(originalStyle));
    }
}