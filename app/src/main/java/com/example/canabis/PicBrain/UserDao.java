package com.example.canabis.PicBrain;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Query("SELECT * FROM user_table WHERE name = :name LIMIT 1")
    User getUserByName(String name);

    @Query("SELECT points FROM user_table WHERE name = :name LIMIT 1")
    int getPointsByName(String name);
}
