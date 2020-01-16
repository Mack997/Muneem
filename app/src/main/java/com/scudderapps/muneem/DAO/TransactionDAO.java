package com.scudderapps.muneem.DAO;

import com.scudderapps.muneem.Model.TransactionData;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TransactionDAO {

    @Insert
    void insert(TransactionData... transactionData);

    @Update
    void update(TransactionData... transactionData);

    @Delete
    void delete(TransactionData... transactionData);

    @Query("SELECT * FROM transactiondata ORDER BY date")
    LiveData<List<TransactionData>> getTransactionData();
}
