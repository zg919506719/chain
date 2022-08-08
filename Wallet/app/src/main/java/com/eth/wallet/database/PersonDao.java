package com.eth.wallet.database;

import com.eth.wallet.database.bean.Person;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface PersonDao {

//    如果 @Insert 方法接收单个参数，则会返回 long 值
//        ，这是插入项的新 rowId。如果参数是数组或集合，则该方法应改为返回由 long 值组成的数组或集合
//        ，并且每个值都作为其中一个插入项的 rowId。如需详细了解如何返回 rowId 值
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Person person);

    @Delete
    void delete(Person person);

    @Update
    void update(Person... persons);

    @Query("SELECT * FROM PERSON_TABLE")
    Single<List<Person>> getAll();

    @Query("SELECT * FROM PERSON_TABLE")
    LiveData<List<Person>> getAllPeople();
}
