package com.example.vacationtourapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;

public class EditProfileActivity extends AppCompatActivity {

    private static final String TAG = "EditProfileActivity";
    // Add this line to define the request code constant
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 101;

    EditText fullName, phone, dob, address;
    ImageView profileImage;
    Button saveButton;

    // Declare the ActivityResultLauncher
    private ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Initialize UI elements
        fullName = findViewById(R.id.edit_full_name);
        phone = findViewById(R.id.edit_phone);
        dob = findViewById(R.id.edit_dob);
        address = findViewById(R.id.edit_address);
        profileImage = findViewById(R.id.edit_profile_image);
        saveButton = findViewById(R.id.save_profile_button);

        // Pre-load existing profile data from SharedPreferences
        loadExistingProfileData();

        // Initialize the ActivityResultLauncher for image selection
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri selectedImageUri = result.getData().getData();
                        if (selectedImageUri != null) {
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                                profileImage.setImageBitmap(bitmap);  // Display the selected image

                                // Save the image as Base64 in SharedPreferences
                                SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("profileImage", ImageUtils.encodeImageToBase64(bitmap));
                                editor.apply();

                            } catch (IOException e) {
                                Log.e(TAG, "Failed to load image", e);
                                Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

        // Set click listener on profile image to open image gallery
        profileImage.setOnClickListener(v -> {
            // Check and request permission to access external storage (Android 11+ compliant)
            if (ContextCompat.checkSelfPermission(
                    getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        EditProfileActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_STORAGE_PERMISSION
                );
            } else {
                selectImageFromGallery();
            }
        });

        // Save button click listener to save changes
        saveButton.setOnClickListener(v -> {
            saveProfileData();  // Save the profile data

            // Pass data back via intent (optional)
            Intent resultIntent = new Intent();
            resultIntent.putExtra("fullName", fullName.getText().toString());
            resultIntent.putExtra("phone", phone.getText().toString());
            resultIntent.putExtra("dob", dob.getText().toString());
            resultIntent.putExtra("address", address.getText().toString());
            setResult(RESULT_OK, resultIntent);

            finish();  // Close EditProfileActivity and go back to ProfileActivity
        });
    }

    // Method to select image from gallery
    private void selectImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            imagePickerLauncher.launch(intent);
        }
    }

    // Handle permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectImageFromGallery();
            } else {
                Log.w(TAG, "Permission denied to read external storage.");
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Load existing profile data from SharedPreferences
    private void loadExistingProfileData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        fullName.setText(sharedPreferences.getString("fullName", ""));
        phone.setText(sharedPreferences.getString("phone", ""));
        dob.setText(sharedPreferences.getString("dob", ""));
        address.setText(sharedPreferences.getString("address", ""));

        // Load the profile image if available
        String profileImageBase64 = sharedPreferences.getString("profileImage", null);
        if (profileImageBase64 != null) {
            Bitmap bitmap = ImageUtils.decodeBase64ToBitmap(profileImageBase64);
            profileImage.setImageBitmap(bitmap);  // Display the saved image
        }
    }

    // Save profile data to SharedPreferences
    private void saveProfileData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("fullName", fullName.getText().toString());
        editor.putString("phone", phone.getText().toString());
        editor.putString("dob", dob.getText().toString());
        editor.putString("address", address.getText().toString());
        editor.apply();
        Log.i(TAG, "Profile data saved successfully.");
    }
}
