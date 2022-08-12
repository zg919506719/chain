package com.eth.wallet.database;

import android.content.Context;

import com.eth.wallet.database.chain.ChainEth;
import com.eth.wallet.database.chain.ChainEthDao;
import com.eth.wallet.database.user.User;
import com.eth.wallet.database.user.UserDao;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities ={User.class,ChainEth.class},version = 1,exportSchema = true,autoMigrations = {
//        @AutoMigration(from = 1,to =2)
})
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME="Wallet.db";

    private static volatile AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context mContext) {
        if (instance==null){
            instance= Room.databaseBuilder(mContext,AppDatabase.class,DB_NAME).build();
        }
        return instance;
    }

    public abstract UserDao getUserDao();

    public abstract ChainEthDao getChainEthDao();
}
