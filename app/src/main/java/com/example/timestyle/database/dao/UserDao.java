package com.example.timestyle.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.timestyle.database.entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM User WHERE email = :email")
    User getUserByUsername(String email);

    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    User checkUser(String email, String password);

    @Query("SELECT * FROM User")
    List<User> getUsers();

    @Query("SELECT * FROM User WHERE id = :id")
    User getUserById(long id);
}
