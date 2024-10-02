package com.example.mobiledevassignment_1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Text Field for user input
    EditText MortgageValue, Interest, Monthlyterm;
    // Show Result of EMI
    TextView EMIoutput;
    // Button for Calculation
    Button calculateButton;

    double principal;
    double interestRate;
    int term;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize views for User Input
        MortgageValue = findViewById(R.id.MortgageInput);
        Interest = findViewById(R.id.interestInput);
        Monthlyterm = findViewById(R.id.MonthlytermInput);

        // Initialize Calculation Elements
        EMIoutput = findViewById(R.id.EMIoutput);
        calculateButton = findViewById(R.id.buttonCalculate);

        // Set up the click listener for the calculate button
        calculateButton.setOnClickListener(v -> {
            // check if the Input values are valid to calculate EMI
            if (InputValues() && ValidateInput()) {
                calculateEMI();
            }
        });
    }

    private boolean InputValues() {
        try {
            // Parse the value from User Input for Mortgage, Interest, and term
            principal = Double.parseDouble(MortgageValue.getText().toString());
            interestRate = Double.parseDouble(Interest.getText().toString()) / 100 / 12;
            term = Integer.parseInt(Monthlyterm.getText().toString());
            return true;

        } catch (NumberFormatException e) {
            // Print error msg if values are invalid
            EMIoutput.setText("Please enter valid values");
            return false;
        }
    }

    private boolean ValidateInput() {
        // check principal value
        if (principal <= 20000) {
            EMIoutput.setText("Please enter principal value greater than 20,000");
            return false;
        }

        // check interest rate
        if (interestRate <= 0) {
            EMIoutput.setText("Please enter Interest value greater than zero");
            return false;
        }

        // check term
        if (term <= 0) {
            EMIoutput.setText("Please enter Term value greater than zero");
            return false;
        }

        return true;
    }

    private void calculateEMI() {

        // EMI calculation formula
        double emi = (principal * interestRate * Math.pow(1 + interestRate, term)) /
                (Math.pow(1 + interestRate, term) - 1);

        // Output EMI value to 2 decimal places
        EMIoutput.setText(String.format("EMI Value: %.2f", emi));
    }
}
