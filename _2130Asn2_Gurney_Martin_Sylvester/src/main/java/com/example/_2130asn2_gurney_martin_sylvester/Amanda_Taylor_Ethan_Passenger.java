package com.example._2130asn2_gurney_martin_sylvester;

// Authors:      Amanda Gurney, Taylor Martin, Ethan Sylvester
// Student IDs:  101443253,     100849882,     101479568
// Course Code:  COMP2130
// Group Number: 13

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Amanda_Taylor_Ethan_Passenger {
    // ATTRIBUTES \\
    String passportID;
    String firstName;
    String lastName;
    String age;
    String flightFare;
    boolean hasBooked;
    LocalDate bookDate;

    // CONSTRUCTOR \\
    Amanda_Taylor_Ethan_Passenger(String ID, String fName, String lName, String age, String fare, boolean booked, LocalDate date) {
        passportID = ID;
        firstName = fName;
        lastName = lName;
        this.age = age;
        flightFare = fare;
        hasBooked = booked;
        bookDate = date;
    }

    // ACCESSORS
    public LocalDate getBookDate() {
        return bookDate;
    }
    public String getPassportID() {
        return passportID;
    }

    // MUTATORS
    public void setHasBooked(boolean hasBooked) {
        this.hasBooked = hasBooked;
    }

    // METHODS
    // OVERRIDE DEFAULT TO STRING METHOD
    // Main Action: Override default string representation of Passenger Object
    public String toString() {
        return  "\n   Passenger Name: "    + "    "    + firstName + " " + lastName +
                "\n   Passport Number: "   + "   "     + passportID +
                "\n   Passenger Age: "     + "       " + age +
                "\n   Flight Fare: "       + "              " + "$" + flightFare +
                "\n   Booking Confirmed: " + hasBooked +
                "\n   Booked Date: "       + "          " + bookDate;
    }

    // Method to save passengers to txt file
    // Loop through list of passengers
    // Convert passenger objects to string representation for file storage
    // Write string representation to file
    // Confirmation message if passengers saved
    // Print stacktrace if error (exception) happens while file writing
    public static void savePassengersToFile(List<Amanda_Taylor_Ethan_Passenger> amandaTaylorEthanPassengers, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Amanda_Taylor_Ethan_Passenger amandaTaylorEthanPassenger : amandaTaylorEthanPassengers) {
                String line = amandaTaylorEthanPassenger.toStringForFile();
                if (line != null) {
                    writer.write(line);
                    writer.newLine();
                }
            }
            System.out.println("Passenger saved to " + filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to load passengers from txt file
    // Read lines from txt file
    // Convert lines back to passenger objects
    // Add passenger objects to list
    // Confirmation message if passengers loaded
    // Print stacktrace if error (exception) happens during file read
    public static List<Amanda_Taylor_Ethan_Passenger> loadPassengersFromFile(String filename) {
        List<Amanda_Taylor_Ethan_Passenger> amandaTaylorEthanPassengers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Amanda_Taylor_Ethan_Passenger amandaTaylorEthanPassenger = Amanda_Taylor_Ethan_Passenger.fromFileString(line);
                if (amandaTaylorEthanPassenger != null) {
                    amandaTaylorEthanPassengers.add(amandaTaylorEthanPassenger);
                }
            }
            System.out.println("Passengers loaded from " + filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return amandaTaylorEthanPassengers;
    }

    // Method to load passengers with recent bookings from txt file (within last 7 days)
    // Read data inside file
    // Create Passenger object from lines
    // Confirm this was successful AND booking date is from last 7 days
    public static List<Amanda_Taylor_Ethan_Passenger> loadRecentBookingsFromFile(String filename) {
        List<Amanda_Taylor_Ethan_Passenger> recentBookings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Amanda_Taylor_Ethan_Passenger amandaTaylorEthanPassenger = Amanda_Taylor_Ethan_Passenger.fromFileString(line);
                if (amandaTaylorEthanPassenger != null) {
                    LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);
                    if (amandaTaylorEthanPassenger.getBookDate().isAfter(sevenDaysAgo)) {
                        recentBookings.add(amandaTaylorEthanPassenger);
                    }
                }
            }
            System.out.println("Recent bookings loaded from " + filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return recentBookings;
    }

    // Method to create Passenger object from a string loaded from txt file
    // Split the line using comma
    // Extract information from the split parts
    // Create a new Passenger object
    // Return the Passenger object
    private static Amanda_Taylor_Ethan_Passenger fromFileString(String line) {
        try {
            String[] parts = line.split(",");

            String passportID = parts[0].trim();
            String firstName = parts[1].trim();
            String lastName = parts[2].trim();
            String age = parts[3].trim();
            String flightFare = parts[4].trim();
            boolean hasBooked = Boolean.parseBoolean(parts[5].trim());
            LocalDate date = LocalDate.parse(parts[6].trim());

            return new Amanda_Taylor_Ethan_Passenger(passportID, firstName, lastName, age, flightFare, hasBooked, date);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to get a string representation for saving to file
    private String toStringForFile() {
        return passportID + "," + firstName + "," + lastName + "," + age + "," +
                flightFare + "," + hasBooked + "," + bookDate;
    }
}