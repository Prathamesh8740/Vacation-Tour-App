package com.example.vacationtourapp.model;


public class Hotel {
    private String name;
    private String address;
    private String price;

    public Hotel(String name, String address, String price) {
        this.name = name;
        this.address = address;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPrice() {
        return price;
    }
}
