package com.tmd.mybudget.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tmd.mybudget.database.Transaction

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions")
    suspend fun getTransactions(): List<Transaction>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(data: Transaction)

    @Query("DELETE FROM transactions WHERE _id = :id")
    suspend fun delete(id: String)
}