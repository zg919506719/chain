package com.eth.wallet.database.user;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE userName=:name")
    User getUser(String name);


// 是否存在   select 1 from user where id = xxx limit 1；
// ||相当于+号 '%' || :searchName || '%'
    @Query("SELECT COUNT(*) FROM User WHERE userName LIKE :name")
    int userExistCount(String name);

    @Insert
    void insertAll(User... users);

    @Update
    void updateUser(User... users);

    @Delete
    void delete(User user);
}
