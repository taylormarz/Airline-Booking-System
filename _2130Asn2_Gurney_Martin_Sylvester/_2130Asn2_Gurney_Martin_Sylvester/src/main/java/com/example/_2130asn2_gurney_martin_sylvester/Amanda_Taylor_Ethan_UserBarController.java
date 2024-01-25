package com.example._2130asn2_gurney_martin_sylvester;

// Authors:      Amanda Gurney, Taylor Martin, Ethan Sylvester
// Student IDs:  101443253,     100849882,     101479568
// Course Code:  COMP2130
// Group Number: 13

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.Optional;

public class Amanda_Taylor_Ethan_UserBarController {

    // VARIABLES
    // FXML file elements
    @FXML
    private Button updateNameButton, updateTitleButton, changePasswordButton, exitButton;

    @FXML
    private Label nameLabel, jobLabel;

    // Method to exit program when exit button is clicked
    @FXML
    private void onExitButtonClick() {
        Platform.exit();
    }

    // Initialize buttons in userprofile bar
    @FXML
    private void initialize() {
        userProfileButtonHoverEvent(updateNameButton);
        userProfileButtonHoverEvent(updateTitleButton);
        userProfileButtonHoverEvent(changePasswordButton);
        userLabels(nameLabel, "Thomas Williamson", 125.0, 14.0, Color.WHITE);
        userLabels(jobLabel, "Booking Agent", 145.0, 12.0, Color.DIMGREY);
        userButtons(updateNameButton, "Update Username", 300.0, 40.0);
        userButtons(updateTitleButton, "Update Title", 340.0, 40.0);
        userButtons(changePasswordButton, "Change Password", 380.0, 40.0);
        userButtons(exitButton, "Exit Application", 725.0, 50.0);
    }

    // Hover event so user gets visual feedback of where their cursor is
    private void userProfileButtonHoverEvent(Button button) {
        String originalStyle = button.getStyle();

        button.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> button.setStyle(originalStyle + "-fx-background-color: #0c5061;"));
        button.addEventHandler(MouseEvent.MOUSE_EXITED, event -> button.setStyle(originalStyle));
    }

    // Method for alert pop up
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Method for changing user profile bar labels
    private void onUserBarButtonClick(Label labelID, String titleText, String contentText) {
        // Create text input dialog box
        TextInputDialog dialog = new TextInputDialog(labelID.getText());
        dialog.setTitle(titleText);
        dialog.setHeaderText(null);
        dialog.setContentText(contentText);

        // Show dialog and wait for user input
        Optional<String> result = dialog.showAndWait();

        // Process user input
        result.ifPresent(newText -> {
            // Validation for user input: compares input against regex
            // If input = valid, update text inside label
            // If not, alert user of invalid input + let them try again
            if (newText.matches("^[A-Za-z]+(['\\s-][A-Za-z]+)*$")) {
                labelID.setText(newText);
            } else {
                showAlert("Invalid Input", "Please enter alphabetic letters only.");
                onUserBarButtonClick(labelID, titleText, contentText);
            }
        });
    }

    // Click event that opens dialog box for user to change username
    @FXML
    private void handleUpdateNameButton() {
        onUserBarButtonClick(nameLabel, "Update Username", "Enter new username:");
    }

    // Click event that opens dialog box for user to change job title
    @FXML
    private void handleUpdateTitleButton() {
        onUserBarButtonClick(jobLabel, "Update Job Title", "Enter new job title:");
    }

    // When change password button is clicked, dialog box pop up for user to enter new password
    // (no label updated just a console confirmation that password was changed successfully)
    @FXML
    private void handleUpdatePasswordButton() {
        // Setting variable to compare against user password validity
        boolean validPasswordEntered = false;

        // While loop, prompts user for valid input until it gets one
        while (!validPasswordEntered) {
            // Creates dialog box for entering new password
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Update Password");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter new password:");

            // Show dialog + wait for user input
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                String newPassword = result.get();
                // Check to make sure password input is not empty or whitespace (user cant hit space bar and enter as password)
                // Exit loop if valid password is given
                if (!newPassword.trim().isEmpty()) {
                    System.out.println("New Password: " + newPassword);
                    validPasswordEntered = true;
                } else {
                    // Password is blank, show an alert
                    showAlert("Invalid Password", "Password cannot be blank. Please try again.");
                }
            } else {
                // Exit loop if user cancels
                break;
            }
        }
    }

    // UI METHOD
    // Creating method for user labels to set some parameters (avoiding code redundancy in fxml file)
    private void userLabels(Label label, String text, double layoutY, double fontSize, Color textFill) {
        label.setText(text);
        label.setLayoutY(layoutY);

        Font font = new Font("Arial", fontSize);
        label.setFont(font);

        label.setStyle("-fx-alignment: center;");
        label.setTextFill(textFill);
    }

    // Creating method for user buttons to set some parameters (avoiding code redundancy in fxml file)
    private void userButtons(Button button, String text, double layoutY, double prefHeight) {
        button.setText(text);
        button.setLayoutY(layoutY);
        button.setPrefHeight(prefHeight);
    }
}