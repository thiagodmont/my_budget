package com.tmd.mybudget.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tmd.mybudget.domain.model.TransactionModel
import com.tmd.mybudget.domain.model.toDatabaseTransactionType
import java.util.*

@Entity(tableName = "transactions")
data class Transaction (
    @PrimaryKey
    val _id: String,
    @ColumnInfo(name = "transaction_type")
    val type: String,
    val description: String,
    val amount: Double,
    val createdAt: Date,
)

fun Transaction.asDomainModel(): TransactionModel {
    return TransactionModel(
        _id = _id,
        type = type.toDatabaseTransactionType(),
        description = description,
        amount = amount,
        createdAt = createdAt
    )
}