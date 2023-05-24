package com.example.timestyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CustomerSupport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_support);
    }

    public void sendCustomerSupport(View view) {
        Toast.makeText(CustomerSupport.this, "Thank you for reporting!", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(CustomerSupport.this, HomeActivity.class);
        startActivity(intent);
    }
}