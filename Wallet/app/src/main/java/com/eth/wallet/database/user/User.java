package com.eth.wallet.database.user;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"userName"},unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    long userId;

    @ColumnInfo
    String userName;

    //先是本地校验
    @ColumnInfo
    String password;
}
