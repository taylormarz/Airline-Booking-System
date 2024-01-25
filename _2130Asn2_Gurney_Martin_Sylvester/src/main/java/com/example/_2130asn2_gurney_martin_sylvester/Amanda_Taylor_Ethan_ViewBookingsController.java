package com.example._2130asn2_gurney_martin_sylvester;

// Authors:      Amanda Gurney, Taylor Martin, Ethan Sylvester
// Student IDs:  101443253,     100849882,     101479568
// Course Code:  COMP2130
// Group Number: 13

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import java.util.List;

public class Amanda_Taylor_Ethan_ViewBookingsController {
    @FXML
    private ListView<Amanda_Taylor_Ethan_Passenger> passengerListView;

    // Load passengers with recent bookings from txt file and display in the ListView (last 7 days)
    @FXML
    void initialize() {
        List<Amanda_Taylor_Ethan_Passenger> recentBookings = Amanda_Taylor_Ethan_Passenger.loadRecentBookingsFromFile("passengers.txt");
        passengerListView.getItems().addAll(recentBookings);
    }
}