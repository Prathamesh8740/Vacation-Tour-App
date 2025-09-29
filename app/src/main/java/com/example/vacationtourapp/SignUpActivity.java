package com.example.vacationtourapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnSignUp;
    TextView tvLoginLink;  // TextView for login link

    // Firebase Auth instance
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etSignUpEmail);
        etPassword = findViewById(R.id.etSignUpPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvLoginLink = findViewById(R.id.tvSignIn);  // Initialize login link TextView

        btnSignUp.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(SignUpActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Sign up user with Firebase Authentication
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Sign-up successful, update UI and save login state
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(SignUpActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();

                                // Save login state in SharedPreferences (optional)
                                getSharedPreferences("VacationTourPrefs", MODE_PRIVATE)
                                        .edit()
                                        .putBoolean("isLoggedIn", true)
                                        .apply();

                                // Redirect to MainActivity
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                finish(); // To prevent returning to sign-up page
                            } else {
                                // If sign up fails, display a message to the user.
                                Toast.makeText(SignUpActivity.this, "Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // Set onClickListener for the login link
        tvLoginLink.setOnClickListener(view -> {
            // Redirect to SignInActivity when login link is clicked
            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            finish();
        });
    }
}
