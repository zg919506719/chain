package com.eth.wallet.database.chain;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ChainEthDao {
    @Query("SELECT * From ChainEth Where chainUserId=:userId")
    List<ChainEth> getAll(String userId);

    @Insert
    void insert(ChainEth... chainEths);

    @Update
    void update(ChainEth... chainEths);

    @Delete
    void delete(ChainEth chainEth);

}
