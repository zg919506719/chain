package com.eth.wallet.database.chain;

import com.eth.wallet.database.user.User;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(indices = {@Index(value = {"address"},unique = true),@Index("chainUserId")}
,foreignKeys = @ForeignKey(entity = User.class,parentColumns = "userId",childColumns = "chainUserId",onDelete = CASCADE))
public class ChainEth {
    @PrimaryKey(autoGenerate = true)
    long chainId;

    @ColumnInfo
    String address;

    @ColumnInfo
    String publicKey;

    @ColumnInfo
    String secretKey;

    @ColumnInfo
    String mnemonic;

    @ColumnInfo
    String chainUserId;
}
