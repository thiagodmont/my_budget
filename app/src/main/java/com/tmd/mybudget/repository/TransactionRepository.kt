package com.tmd.mybudget.repository

import com.tmd.mybudget.domain.model.TransactionModel

internal interface TransactionRepository {
    suspend fun getTransactions(): List<TransactionModel>

    suspend fun addTransaction(data: TransactionModel)

    suspend fun deleteTransaction(id: String)
}