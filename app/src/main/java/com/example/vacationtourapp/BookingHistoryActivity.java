package com.example.vacationtourapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.vacationtourapp.adapter.BookingHistoryAdapter;
import com.example.vacationtourapp.model.BookingHistory;
import java.util.ArrayList;
import java.util.List;

public class BookingHistoryActivity extends AppCompatActivity {

    RecyclerView bookingHistoryRecyclerView;
    BookingHistoryAdapter bookingHistoryAdapter;
    List<BookingHistory> bookingHistoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);

        // Initialize RecyclerView for booking history
        bookingHistoryRecyclerView = findViewById(R.id.booking_history_recycler_view);
        bookingHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load booking history data
        bookingHistoryList = new ArrayList<>();
        loadBookingHistoryData();  // Populate with mock data or actual data

        bookingHistoryAdapter = new BookingHistoryAdapter(bookingHistoryList);
        bookingHistoryRecyclerView.setAdapter(bookingHistoryAdapter);
    }

    // Method to load mock booking history data
    private void loadBookingHistoryData() {
        // Add some example bookings (You can modify this with real data or API)
        bookingHistoryList.add(new BookingHistory("Flight to Paris", "Completed", "₹10000"));
        bookingHistoryList.add(new BookingHistory("Hotel in Rome", "Cancelled", "₹5000"));
        bookingHistoryList.add(new BookingHistory("Flight to New York", "Pending", "₹3000"));
        // You can add more items here from real data or an API call
    }
}
