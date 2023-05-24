package com.example.timestyle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

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
}