package com.example.timestyle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timestyle.database.AppDatabase;
import com.example.timestyle.database.dao.BagDao;
import com.example.timestyle.database.dao.ProductDao;
import com.example.timestyle.database.dao.WishlistDao;
import com.example.timestyle.database.entity.Bag;
import com.example.timestyle.database.entity.Product;
import com.example.timestyle.database.entity.Wishlist;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView tvPrice;
    private TextView tvProductQuantity;
    private int productQuantity = 1;
    private ProductDao productDao;
    private Product product;
    private long product_id;
    private WishlistDao wishlistDao;
    private long userId;
    private ImageButton ibWishlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        productDao = AppDatabase.getInstance(this).productDao();
        wishlistDao = AppDatabase.getInstance(this).wishlistDao();

        SharedPreferences userPrefs = getSharedPreferences("userPrefs", MODE_PRIVATE);
        userId = userPrefs.getLong("user_id", 0);
        Intent intent = getIntent();
        product_id = intent.getExtras().getLong("id");
        product = productDao.getProductById(product_id);

        TextView tvName = findViewById(R.id.tv_name_detail);
        TextView tvDescription = findViewById(R.id.tv_description_detail);
        tvPrice = findViewById(R.id.tv_price_detail);
        ImageView ivImage = findViewById(R.id.iv_image_detail);
        tvProductQuantity = findViewById(R.id.tv_quantity_detail);

        String price = "$" + product.price;
        tvPrice.setText(price);

        String strQuantity = String.valueOf(productQuantity);
        tvProductQuantity.setText(strQuantity);
        tvName.setText(product.name);
        tvDescription.setText(product.description);
        Bitmap bmpImage = ImageHelper.convertByteArrayToBitmap(product.image);
        ivImage.setImageBitmap(bmpImage);

        ibWishlist = findViewById(R.id.ib_wish_list);
        if (wishlistDao.getWishlistByUserIdAndProductId(userId, product_id) != null) {
            ibWishlist.setImageResource(R.drawable.heart_filled);
        }
    }

    public void decrementQuantity(View view) {
        if (productQuantity > 1) {
            productQuantity--;
        }
        String realPrice = "" + productQuantity;
        tvProductQuantity.setText(realPrice);
        modifyPriceByQuantity();
    }

    public void incrementQuantity(View view) {
        productQuantity++;
        String realPrice = "" + productQuantity;
        tvProductQuantity.setText(realPrice);
        modifyPriceByQuantity();
    }

    public void modifyPriceByQuantity() {
        String price = "$" + product.price;
        tvPrice.setText(price);
    }

    public void goBack(View view) {
        onBackPressed();
    }

    public void addToBag(View view) {
        SharedPreferences userPrefs = getSharedPreferences("userPrefs", MODE_PRIVATE);
        long user_id = userPrefs.getLong("user_id", 0);
        Bag bag = new Bag(user_id, product_id, productQuantity);
        BagDao bagDao = AppDatabase.getInstance(this).bagDao();
        bagDao.insert(bag);
        Toast.makeText(ProductDetailActivity.this, "Successfully Added to bag", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(ProductDetailActivity.this, MyBagActivity.class);
        startActivity(intent);
    }

    public void addToWishlist(View view) {
        Wishlist wishlist = wishlistDao.getWishlistByUserIdAndProductId(userId, product_id);
        if (wishlist != null) {
            ibWishlist.setImageResource(R.drawable.heart_filled);
            ibWishlist.setEnabled(true);
            wishlistDao.delete(wishlist);
        } else {
            wishlistDao.insert(new Wishlist(userId, product_id));
            ibWishlist.setImageResource(R.drawable.heart_filled);

            Intent intent = new Intent(ProductDetailActivity.this, WishlistActivity.class);
            startActivity(intent);
        }

    }
}