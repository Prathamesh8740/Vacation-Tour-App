package com.example.vacationtourapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vacationtourapp.adapter.RecentsAdapter;
import com.example.vacationtourapp.adapter.TopPlacesAdapter;
import com.example.vacationtourapp.model.RecentsData;
import com.example.vacationtourapp.model.TopPlacesData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;
    TopPlacesAdapter topPlacesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initializing RecyclerView and adding dummy data
        List<RecentsData> recentsDataList = new ArrayList<>();
        recentsDataList.add(new RecentsData("AM Lake", "India", "From $200", R.drawable.recentimage1));
        recentsDataList.add(new RecentsData("Nilgiri Hills", "India", "From $300", R.drawable.recentimage2));
        recentsDataList.add(new RecentsData("AM Lake", "India", "From $200", R.drawable.recentimage1));
        recentsDataList.add(new RecentsData("Nilgiri Hills", "India", "From $300", R.drawable.recentimage2));
        recentsDataList.add(new RecentsData("AM Lake", "India", "From $200", R.drawable.recentimage1));
        recentsDataList.add(new RecentsData("Nilgiri Hills", "India", "From $300", R.drawable.recentimage2));

        setRecentRecycler(recentsDataList);

        List<TopPlacesData> topPlacesDataList = new ArrayList<>();
        topPlacesDataList.add(new TopPlacesData("Kashmir Hills", "India", "$200 - $500", R.drawable.topplaces));
        topPlacesDataList.add(new TopPlacesData("Kashmir Hills", "India", "$200 - $500", R.drawable.topplaces));
        topPlacesDataList.add(new TopPlacesData("Kashmir Hills", "India", "$200 - $500", R.drawable.topplaces));
        topPlacesDataList.add(new TopPlacesData("Kashmir Hills", "India", "$200 - $500", R.drawable.topplaces));
        topPlacesDataList.add(new TopPlacesData("Kashmir Hills", "India", "$200 - $500", R.drawable.topplaces));
        topPlacesDataList.add(new TopPlacesData("Kashmir Hills", "India", "$200 - $500", R.drawable.topplaces));

        setTopPlacesRecycler(topPlacesDataList);

        // Setting up navigation for Profile, Flights, and Hotels
        ImageView profileIcon = findViewById(R.id.profile_button);
        ImageView flightIcon = findViewById(R.id.flights_button);
        ImageView hotelIcon = findViewById(R.id.hotel_button);

        profileIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        flightIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FlightActivity.class);
            startActivity(intent);
        });

        hotelIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HotelActivity.class);
            startActivity(intent);
        });

        // Adjusting insets for the edge-to-edge experience
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setRecentRecycler(List<RecentsData> recentsDataList) {
        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(this, recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);
    }

    private void setTopPlacesRecycler(List<TopPlacesData> topPlacesDataList) {
        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new TopPlacesAdapter(this, topPlacesDataList);
        topPlacesRecycler.setAdapter(topPlacesAdapter);
    }
}


