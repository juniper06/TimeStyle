package com.example.timestyle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.example.timestyle.adapter.ProductAdapter;
import com.example.timestyle.database.AppDatabase;
import com.example.timestyle.database.dao.ProductDao;
import com.example.timestyle.database.entity.Product;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ProductDao productDao = AppDatabase.getInstance(this).productDao();
        List<Product> productList;

        productList = new ArrayList<>();

//        load(productDao);
        try {
            productList.addAll(productDao.getProducts());
        } catch (NullPointerException ignored) {
        }

        RecyclerView product_item_rv = findViewById(R.id.rv_product);
        ProductAdapter productAdapter = new ProductAdapter(this, productList);
        product_item_rv.setAdapter(productAdapter);
    }

    private void load(ProductDao productDao){
        Bitmap pic1 = BitmapFactory.decodeResource(getResources(), R.drawable.pic1);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        pic1.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] pic1ByteArray = stream.toByteArray();
        productDao.insert(new Product(pic1ByteArray, "Watch", "This is a beautiful watch", 3000));

        Bitmap pic2 = BitmapFactory.decodeResource(getResources(), R.drawable.pic2);
        stream = new ByteArrayOutputStream();
        pic2.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] pic2ByteArray = stream.toByteArray();
        productDao.insert(new Product(pic2ByteArray, "Clothes", "This is a wonderful cloth", 4000));
    }

    public void openBag(View view) {
        Intent intent = new Intent(HomeActivity.this, MyBagActivity.class);
        startActivity(intent);
    }

    public void openWishlist(View view) {
        Intent intent = new Intent(HomeActivity.this, WishlistActivity.class);
        startActivity(intent);
    }

    public void openCustomerSupport(View view) {
        Intent intent = new Intent(HomeActivity.this, CustomerSupport.class);
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