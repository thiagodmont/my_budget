package com.tmd.mybudget.domain.model

import com.tmd.mybudget.database.Transaction
import java.util.*

data class TransactionModel(
    val _id: String,
    val type: TransactionType,
    val description: String,
    val amount: Double,
    val createdAt: Date,
)

fun TransactionModel.asDatabaseModel(): Transaction {
    return Transaction(
        _id = _id,
        type = type.data,
        description = description,
        amount = amount,
        createdAt = createdAt
    )
}

enum class TransactionType(val data: String) {
    Income("I"),
    Expenses("E")
}

fun String.toModelTransactionType(): TransactionType {
    return when(this) {
        "Income" -> TransactionType.Income
        "Expenses" -> TransactionType.Expenses
        else -> throw IllegalArgumentException("Wrong transaction type")
    }
}

fun String.toDatabaseTransactionType(): TransactionType {
    return when(this) {
        "I" -> TransactionType.Income
        "E" -> TransactionType.Expenses
        else -> throw IllegalArgumentException("Wrong transaction type")
    }
}
