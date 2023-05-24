package com.example.timestyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.timestyle.database.AppDatabase;
import com.example.timestyle.database.dao.UserDao;
import com.example.timestyle.database.entity.User;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences userPrefs = getSharedPreferences("userPrefs", MODE_PRIVATE);
        if (userPrefs.getLong("user_id", 0) != 0) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    public void loginUser(View view) {
        EditText etEmail = findViewById(R.id.et_email1);
        EditText etPassword = findViewById(R.id.et_password1);

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        UserDao userDao = AppDatabase.getInstance(this).userDao();
        User user = userDao.checkUser(email, password);
        if(user != null) {
            SharedPreferences userPrefs = getSharedPreferences("userPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = userPrefs.edit();
            editor.putLong("user_id", user.id);
            editor.apply();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_LONG).show();
        }

    }

    public void openRegisterActivity(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}