package com.example.vacationtourapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {

    ImageView profileImage;
    TextView fullName, phone, dob, address;
    Button editProfileButton, logoutButton; // Add logout button
    TextView notificationSection;
    LinearLayout bookingHistoryLayout; // Declare LinearLayout for booking history
    ImageView facebookIcon, twitterIcon, instagramIcon, linkedinIcon; // Added LinkedIn icon

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize UI elements
        profileImage = findViewById(R.id.profile_image);
        fullName = findViewById(R.id.profile_full_name);
        phone = findViewById(R.id.profile_phone);
        dob = findViewById(R.id.profile_dob);
        address = findViewById(R.id.profile_address);
        editProfileButton = findViewById(R.id.edit_profile_button);
        logoutButton = findViewById(R.id.btn_logout); // Initialize logout button
        notificationSection = findViewById(R.id.notification_section);
        bookingHistoryLayout = findViewById(R.id.booking_history_layout); // Initialize bookingHistoryLayout
        facebookIcon = findViewById(R.id.facebook_icon);
        twitterIcon = findViewById(R.id.twitter_icon);
        instagramIcon = findViewById(R.id.instagram_icon);
        linkedinIcon = findViewById(R.id.linkedin_icon); // Initialize LinkedIn icon

        // Social Media Click Listeners
        facebookIcon.setOnClickListener(v -> openSocialMedia("https://www.facebook.com/login/"));
        twitterIcon.setOnClickListener(v -> openSocialMedia("https://x.com/twitt_login"));
        instagramIcon.setOnClickListener(v -> openSocialMedia("https://www.instagram.com/?hl=en"));
        linkedinIcon.setOnClickListener(v -> openSocialMedia("https://www.linkedin.com/feed/")); // LinkedIn click listener

        // Load profile data from SharedPreferences
        loadProfileData();

        // Edit profile button click listener
        editProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
            startActivity(intent);
        });

        // Set click listener for Notifications section to open NotificationsActivity
        notificationSection.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, NotificationsActivity.class);
            startActivity(intent);
        });

        // Set click listener for Booking History section to open BookingHistoryActivity
        bookingHistoryLayout.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, BookingHistoryActivity.class);
            startActivity(intent);
        });

        // Set click listener for logout button
        logoutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut(); // Sign out from Firebase
            SharedPreferences sharedPreferences = getSharedPreferences("VacationTourPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLoggedIn", false); // Update login state
            editor.apply();

            // Redirect to SignInActivity
            Intent intent = new Intent(ProfileActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
        });
    }

    // Method to open social media link in a browser
    private void openSocialMedia(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProfileData();  // Load profile data again when returning from EditProfileActivity
    }

    // Method to load profile image from SharedPreferences
    private void loadProfileImage() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        String base64Image = sharedPreferences.getString("profileImage", null);

        if (base64Image != null) {
            Bitmap bitmap = ImageUtils.decodeBase64ToBitmap(base64Image);
            profileImage.setImageBitmap(bitmap);  // Display the saved image
        } else {
            profileImage.setImageResource(R.drawable.ic_user);  // Set default image if no image is saved
        }
    }

    // Method to load profile data from SharedPreferences
    private void loadProfileData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        fullName.setText(sharedPreferences.getString("fullName", "John Doe"));
        phone.setText(sharedPreferences.getString("phone", "+1234567890"));
        dob.setText(sharedPreferences.getString("dob", "01/01/1990"));
        address.setText(sharedPreferences.getString("address", "123 Main Street, City, Country"));

        // Load the profile image
        loadProfileImage();
    }
}


