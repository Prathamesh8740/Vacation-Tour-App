package com.example.vacationtourapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vacationtourapp.adapter.HotelAdapter;
import com.example.vacationtourapp.model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelActivity extends AppCompatActivity {

     EditText hotelLocation;
     EditText checkInDate;
     EditText checkOutDate;
     Button searchHotelsButton;
     RecyclerView hotelRecyclerView;
     HotelAdapter hotelAdapter;
     List<Hotel> hotelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        hotelLocation = findViewById(R.id.hotel_location);
        checkInDate = findViewById(R.id.check_in_date);
        checkOutDate = findViewById(R.id.check_out_date);
        searchHotelsButton = findViewById(R.id.search_hotels_button);
        hotelRecyclerView = findViewById(R.id.hotel_recycler_view);

        // Initialize hotel list and adapter
        hotelList = new ArrayList<>();
        hotelAdapter = new HotelAdapter(hotelList);

        hotelRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        hotelRecyclerView.setAdapter(hotelAdapter);

        // Sample data
        loadSampleHotels();

        searchHotelsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle search button click
                // You might want to implement search functionality here
            }
        });
    }

    private void loadSampleHotels() {
        hotelList.add(new Hotel("Hotel Paradise", "123 Beach Road", "₹9600"));
        hotelList.add(new Hotel("Mountain Retreat", "456 Hilltop Drive", "₹12000"));
        hotelList.add(new Hotel("City Center Inn", "789 Downtown Blvd", "₹8000"));
        hotelAdapter.notifyDataSetChanged();
    }
}
