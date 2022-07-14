package com.tmd.mybudget.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tmd.mybudget.dao.TransactionDao

@Database(entities = [Transaction::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class MyBudgetDatabase: RoomDatabase() {
    abstract val transaction: TransactionDao
}