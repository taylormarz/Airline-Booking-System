package com.example._2130asn2_gurney_martin_sylvester;

// Authors:      Amanda Gurney, Taylor Martin, Ethan Sylvester
// Student IDs:  101443253,     100849882,     101479568
// Course Code:  COMP2130
// Group Number: 13

import javafx.scene.control.Alert;
import java.time.LocalDate;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Amanda_Taylor_Ethan_Flight {
    // ATTRIBUTES
    String flightNumber;
    LocalDate date;
    String source;
    String destination;
    Amanda_Taylor_Ethan_Passenger[] amandaTaylorEthanPassengers;
    int maxPassenger;
    int currentCount;

    // CONSTRUCTOR
    // Main Action: Initialize variables in Flight Class
    Amanda_Taylor_Ethan_Flight(String flightNumber, LocalDate date, String source, String destination, int maxPassenger) {
        this.flightNumber = flightNumber;
        this.date         = date;
        this.source       = source;
        this.destination  = destination;
        this.amandaTaylorEthanPassengers = new Amanda_Taylor_Ethan_Passenger[maxPassenger];
        this.currentCount = 0;
        this.maxPassenger = maxPassenger;
    }

    // ACCESSOR
    public Amanda_Taylor_Ethan_Passenger[] getPassengers() {
        return amandaTaylorEthanPassengers;
    }

    // METHODS
    // Alert method for validation in class
    // Create alert class instance
    // Set title of alert, set content text, display alert and wait for to clear it (cancel)
    private void showAlertDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Add passenger to flight method
    // Check if the passenger with the same passport ID as entered already exists in the flight manifest
    // Iterate through passengers in manifest
    // Check if current manifest slot is empty
    // If customer added already, pop up alert to inform user
    public void addPassenger(Amanda_Taylor_Ethan_Passenger amandaTaylorEthanPassenger) {
        for (Amanda_Taylor_Ethan_Passenger value : amandaTaylorEthanPassengers) {
            if (value != null) {
                if (Objects.equals(amandaTaylorEthanPassenger.passportID, value.passportID)) {
                    showAlertDialog("Duplicate Passenger", "This passenger has already been added to the flight manifest.");
                    return;
                }
            }
        }

        // Check if the flight has reached its maximum capacity
        // If not at maximum capacity, add user to manifest of selected flight (alert to inform user passenger is added)
        // If max capacity reached, passenger not added (alert to tell user passenger not added and reason)
        if (currentCount < maxPassenger) {
            amandaTaylorEthanPassengers[currentCount] = amandaTaylorEthanPassenger;
            currentCount++;
            showAlertDialog("Passenger Added to Manifest", "Successfully added " + amandaTaylorEthanPassenger.firstName + " to the flight manifest.");
        } else {
            showAlertDialog("Capacity Reached", "Sorry, this flight has reached capacity.");
        }
    }

    // OVERRIDE DEFAULT TO STRING METHOD
    // Main Action: Override default string representation of Flight Object
    public String toString() {
        return  "\n   Flight Number: "    + flightNumber +
                "\n   Date: "             + date +
                "\n   Source: "           + source +
                "\n   Destination: "      + destination +
                "\n   Current Capacity: " + currentCount +
                "\n   Maximum Capacity: " + maxPassenger;
    }

    // Method to save flights to txt file
    // Loop through list of flights
    // Convert flight objects to string representation for file storage
    // Write string representation to file
    // Confirmation message if flights saved
    // Print stacktrace if error (exception) happens while file writing
    public static void saveFlightsToFile(List<Amanda_Taylor_Ethan_Flight> amandaTaylorEthanFlights, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Amanda_Taylor_Ethan_Flight amandaTaylorEthanFlight : amandaTaylorEthanFlights) {
                String line = amandaTaylorEthanFlight.toStringForFile();
                if (line != null) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            System.out.println("Flights saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load flights from txt file
    // Read lines from txt file
    // Convert lines back to flight objects
    // Add flight objects to list
    // Confirmation message if flights loaded
    // Print stacktrace if error (exception) happens during file read
    public static List<Amanda_Taylor_Ethan_Flight> loadFlightsFromFile(String filename) {
        List<Amanda_Taylor_Ethan_Flight> amandaTaylorEthanFlights = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Amanda_Taylor_Ethan_Flight amandaTaylorEthanFlight = Amanda_Taylor_Ethan_Flight.fromFileString(line);
                if (amandaTaylorEthanFlight != null) {
                    amandaTaylorEthanFlights.add(amandaTaylorEthanFlight);
                }
            }
            System.out.println("Flights loaded from " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return amandaTaylorEthanFlights;
    }

    // Method to create Flight object from a string loaded from txt file
    // Split the line using comma
    // Extract information from the split parts
    // Create a new Flight object
    // Set the currentCount after creating the object
    // Return the Flight object
    private static Amanda_Taylor_Ethan_Flight fromFileString(String line) {
        try {
            String[] parts = line.split(",");

            String flightNumber = parts[0].trim();
            LocalDate date = LocalDate.parse(parts[1].trim());
            String source = parts[2].trim();
            String destination = parts[3].trim();
            int currentCount = Integer.parseInt(parts[4].trim());
            int maxPassenger = Integer.parseInt(parts[5].trim());

            Amanda_Taylor_Ethan_Flight amandaTaylorEthanFlight = new Amanda_Taylor_Ethan_Flight(flightNumber, date, source, destination, maxPassenger);
            amandaTaylorEthanFlight.currentCount = currentCount;
            return amandaTaylorEthanFlight;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to get a string representation for saving to a file
    private String toStringForFile() {
        return flightNumber + "," + date + "," + source + "," + destination + "," +
                currentCount + "," + maxPassenger;
    }
}