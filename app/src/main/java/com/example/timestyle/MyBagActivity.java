package com.example.timestyle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.timestyle.adapter.BagAdapter;
import com.example.timestyle.adapter.ProductAdapter;
import com.example.timestyle.database.AppDatabase;
import com.example.timestyle.database.dao.BagDao;
import com.example.timestyle.database.entity.Bag;
import com.example.timestyle.database.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class MyBagActivity extends AppCompatActivity {

    private List<Bag> bagList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bag);

        SharedPreferences userPrefs = getSharedPreferences("userPrefs", MODE_PRIVATE);
        long user_id = userPrefs.getLong("user_id", 0);
        bagList = new ArrayList<>();
        BagDao bagDao = AppDatabase.getInstance(this).bagDao();
        try {
            bagList.addAll(bagDao.getUserWithBags(user_id));
        } catch (NullPointerException ignored){}
        RecyclerView rvProduct = findViewById(R.id.product_item_rv);
        BagAdapter bagAdapter = new BagAdapter(this, bagList);
        rvProduct.setAdapter(bagAdapter);
    }

    public void goBack(View view){
        onBackPressed();
    }

    public void checkOut(View view) {
        BagDao bagDao = AppDatabase.getInstance(this).bagDao();
        bagDao.deleteAll();
        createNotification();
        Intent intent = new Intent(MyBagActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notification", "TimeStyle Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        // Create an intent to launch the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MyBagActivity.this, "My Notification");
        builder.setContentTitle("Congratulation!");
        builder.setContentText("Thanks for purchasing!");
        builder.setSmallIcon(R.drawable.customersupport);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MyBagActivity.this);
        managerCompat.notify(1, builder.build());
    }

    public void openCustomerSupport(View view) {
        Intent intent = new Intent(MyBagActivity.this, CustomerSupport.class);
        startActivity(intent);
    }
    public void shareLink(View view) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share this app");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "https://www.facebook.com/junipergabriel6");
        startActivity(Intent.createChooser(shareIntent, "Share Link"));
    }
}