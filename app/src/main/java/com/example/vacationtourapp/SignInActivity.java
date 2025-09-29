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
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class SignInActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnSignIn;
    TextView tvSignUpLink; // Declare TextView for the sign-up link

    // Declare an instance of FirebaseAuth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Bind views from XML
        etEmail = findViewById(R.id.etSignInEmail);
        etPassword = findViewById(R.id.etSignInPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvSignUpLink = findViewById(R.id.tvSignIn); // Bind the sign-up link TextView

        // Set onClickListener for the SignIn button
        btnSignIn.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            // Validate email and password fields
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(SignInActivity.this, "Enter email!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(SignInActivity.this, "Enter password!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Sign in with Firebase Authentication
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign-in success, navigate to MainActivity
                                Toast.makeText(SignInActivity.this, "Sign-in successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish(); // Close SignInActivity
                            } else {
                                // If sign-in fails, display a message to the user
                                Toast.makeText(SignInActivity.this, "Sign-in failed. Check your credentials", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });

        // Set onClickListener for the sign-up link
        tvSignUpLink.setOnClickListener(view -> {
            // Navigate to SignUpActivity
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish(); // Optionally close the SignInActivity
        });
    }
}
