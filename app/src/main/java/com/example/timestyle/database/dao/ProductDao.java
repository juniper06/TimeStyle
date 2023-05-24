package com.example.timestyle.database.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.timestyle.database.entity.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insert(Product product);

    @Query("SELECT * FROM Product")
    List<Product> getProducts();

    @Query("DELETE FROM Product")
    void deleteAll();

    @Query("SELECT * FROM Product WHERE id = :id")
    Product getProductById(long id);
}
