package com.example.timestyle.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.timestyle.database.dao.BagDao;
import com.example.timestyle.database.dao.ProductDao;
import com.example.timestyle.database.dao.UserDao;
import com.example.timestyle.database.entity.Bag;
import com.example.timestyle.database.entity.Product;
import com.example.timestyle.database.entity.User;

@Database(entities = {User.class, Product.class, Bag.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract ProductDao productDao();
    public abstract BagDao bagDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                    "test_db5").allowMainThreadQueries().build();
        }
        return instance;
    }


}
