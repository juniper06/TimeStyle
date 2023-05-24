package com.example.timestyle.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Bag {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public long userId;
    public long productId;
    public int quantity;

    public Bag(long userId, long productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }
}