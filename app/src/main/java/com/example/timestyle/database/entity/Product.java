package com.example.timestyle.database.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public byte[] image;
    public String name;
    public String description;
    public double price;

    public Product(byte[] image, String name, String description, double price) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
