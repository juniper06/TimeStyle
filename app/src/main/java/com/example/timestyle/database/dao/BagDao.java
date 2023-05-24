package com.example.timestyle.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.timestyle.database.entity.Bag;

import java.util.List;

@Dao
public interface BagDao {
    @Insert
    void insert(Bag bag);

    @Delete
    void delete(Bag bag);

    @Query("DELETE FROM Bag")
    void deleteAll();

    @Query("SELECT * FROM Bag WHERE userId = :userId")
    List<Bag> getUserWithBags(long userId);
}