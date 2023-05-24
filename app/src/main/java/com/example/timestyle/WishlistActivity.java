package com.example.timestyle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.timestyle.adapter.WishlistAdapter;
import com.example.timestyle.database.AppDatabase;
import com.example.timestyle.database.dao.WishlistDao;
import com.example.timestyle.database.entity.Wishlist;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {

    private List<Wishlist> wishlists;
    private long userId;
    private WishlistDao wishlistDao;
    private WishlistAdapter wishlistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        SharedPreferences userPrefs = getSharedPreferences("userPrefs", MODE_PRIVATE);
        userId = userPrefs.getLong("user_id", 0);
        wishlistDao = AppDatabase.getInstance(this).wishlistDao();

        initData();
        RecyclerView cart_item_rv = findViewById(R.id.product_item_rv);
        wishlistAdapter = new WishlistAdapter(this, wishlists);
        cart_item_rv.setAdapter(wishlistAdapter);

    }

    public void goBack(View view) {
        onBackPressed();
    }

    private void initData() {
        wishlists = new ArrayList<>();
        List<Wishlist> wishlists1 = wishlistDao.getWishlistByUserId(userId);
        if (wishlists1 != null)
            wishlists.addAll(wishlists1);
    }

    public void openCustomerSupport(View view) {
        Intent intent = new Intent(WishlistActivity.this, CustomerSupport.class);
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