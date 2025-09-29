package com.example.vacationtourapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        // Here you can load notifications from SharedPreferences, a database, or an API
        loadNotifications();
    }

    private void loadNotifications() {
        // Placeholder for loading notifications, you can populate actual data here.
        // For example, retrieve from SharedPreferences or a database and display them.
    }
}
