package com.example.vacationtourapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vacationtourapp.adapter.FlightAdapter;
import com.example.vacationtourapp.model.Flight;
import java.util.ArrayList;
import java.util.List;

public class FlightActivity extends AppCompatActivity {

    EditText departureLocation;
    EditText destinationLocation;
    Button searchFlightsButton;
    RecyclerView flightRecyclerView;
    FlightAdapter flightAdapter;
    List<Flight> flightList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);

        departureLocation = findViewById(R.id.departure_location);
        destinationLocation = findViewById(R.id.destination_location);
        searchFlightsButton = findViewById(R.id.search_flights_button);
        flightRecyclerView = findViewById(R.id.flight_recycler_view);

        // Initialize flight list and adapter
        flightList = new ArrayList<>();
        flightAdapter = new FlightAdapter(flightList);

        flightRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        flightRecyclerView.setAdapter(flightAdapter);

        // Sample data
        loadSampleFlights();

        searchFlightsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle search button click
                // Implement search functionality here
            }
        });
    }

    private void loadSampleFlights() {
        flightList.add(new Flight("FL123", "10:00 AM", "12:00 PM", "₹24000"));
        flightList.add(new Flight("FL456", "02:00 PM", "04:00 PM", "₹20000"));
        flightList.add(new Flight("FL789", "06:00 PM", "08:00 PM", "₹16000"));
        flightAdapter.notifyDataSetChanged();
    }
}
