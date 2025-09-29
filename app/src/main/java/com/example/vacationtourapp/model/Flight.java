package com.example.vacationtourapp.model;

public class Flight {
    private String flightNumber;
    private String departureTime;
    private String arrivalTime;
    private String price;

    public Flight(String flightNumber, String departureTime, String arrivalTime, String price) {
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getPrice() {
        return price;
    }
}
