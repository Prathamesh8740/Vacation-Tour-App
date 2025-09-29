package com.example.vacationtourapp;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class BookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking);

        TextView bookingDetails = findViewById(R.id.textViewBookingDetails);
        // Here, you can retrieve the booking information you passed from DetailsActivity and set it to the TextView.

        Button confirmBookingButton = findViewById(R.id.buttonConfirmBooking);
        confirmBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle booking confirmation logic here
            }
        });
    }
}