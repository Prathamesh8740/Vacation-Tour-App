package com.example.vacationtourapp.model;

public class BookingHistory {

    private String bookingName;
    private String bookingStatus;
    private String bookingPrice;

    // Constructor
    public BookingHistory(String bookingName, String bookingStatus, String bookingPrice) {
        this.bookingName = bookingName;
        this.bookingStatus = bookingStatus;
        this.bookingPrice = bookingPrice;
    }

    // Getters
    public String getBookingName() {
        return bookingName;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public String getBookingPrice() {
        return bookingPrice;
    }
}
