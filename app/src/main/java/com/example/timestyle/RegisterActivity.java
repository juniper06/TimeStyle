package com.example.timestyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.timestyle.database.AppDatabase;
import com.example.timestyle.database.dao.UserDao;
import com.example.timestyle.database.entity.User;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void registerUser(View view) {

        UserDao userDao = AppDatabase.getInstance(this).userDao();
        EditText etFirstname = findViewById(R.id.et_firstname);
        EditText etLastname = findViewById(R.id.et_lastname);
        EditText etEmail = findViewById(R.id.et_email);
        EditText etPassword = findViewById(R.id.et_password);

        String firstname = etFirstname.getText().toString();
        String lastname = etLastname.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        User user = new User(firstname, lastname, email, password);
        userDao.insert(user);
        Toast.makeText(this, "Successfully registered", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void openLoginActivity(View view) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}