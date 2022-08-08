package com.eth.wallet.database.bean;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.RenameTable;

@Entity(tableName = "person_table")
public class Person {
    @PrimaryKey(autoGenerate = true)
    long id;

    @ColumnInfo
    String name;

    @ColumnInfo
    String age;

    @Ignore
    Bitmap photo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", photo=" + photo +
                '}';
    }
}
