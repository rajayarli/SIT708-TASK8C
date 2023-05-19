package com.example.a8c;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private EditText fullNameEditText;
    private EditText userNameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullNameEditText = findViewById(R.id.editTextText3);
        userNameEditText = findViewById(R.id.editTextText4);
        passwordEditText = findViewById(R.id.editTextTextPassword2);
        confirmPasswordEditText = findViewById(R.id.editTextTextPassword3);
        createAccountButton = findViewById(R.id.button6);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = fullNameEditText.getText().toString();
                String userName = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String cpassword = confirmPasswordEditText.getText().toString();
                int bool = 0;
                if (fullName.isEmpty()) {
                    Toast.makeText(SignUp.this, "Enter Full Name", Toast.LENGTH_SHORT).show();
                    bool = 1;
                }
                if (userName.isEmpty()) {
                    Toast.makeText(SignUp.this, "Enter User Name", Toast.LENGTH_SHORT).show();
                    bool = 1;
                }
                if (password.isEmpty()) {
                    Toast.makeText(SignUp.this, "Enter password Name", Toast.LENGTH_SHORT).show();
                    bool = 1;
                }
                if (cpassword.isEmpty()) {
                    Toast.makeText(SignUp.this, "Confirm Password again", Toast.LENGTH_SHORT).show();
                    bool = 1;
                }
                if (!cpassword.equals(password)) {
                    Toast.makeText(SignUp.this, "Check whether the both passwords are same or not", Toast.LENGTH_SHORT).show();
                    bool = 1;
                }
                if (bool == 0) {
                    // Create a new instance of the User class with the entered details
                    User newUser = new User(0, fullName, userName, password);

                    // Store the user details in the database
                    DatabaseHelper dbHelper = new DatabaseHelper(SignUp.this);
                    dbHelper.addUser(newUser);

                    Toast.makeText(SignUp.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
