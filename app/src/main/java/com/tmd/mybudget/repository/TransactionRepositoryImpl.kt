package com.tmd.mybudget.repository

import com.tmd.mybudget.database.MyBudgetDatabase
import com.tmd.mybudget.database.asDomainModel
import com.tmd.mybudget.domain.model.TransactionModel
import com.tmd.mybudget.domain.model.asDatabaseModel

internal class TransactionRepositoryImpl (
    private val database: MyBudgetDatabase
): TransactionRepository {
    override suspend fun getTransactions(): List<TransactionModel> {
        return database.transaction.getTransactions().map {
            it.asDomainModel()
        }
    }

    override suspend fun addTransaction(data: TransactionModel) {
        return database.transaction.insert(
            data = data.asDatabaseModel()
        )
    }

    override suspend fun deleteTransaction(id: String) {
        return database.transaction.delete(
            id = id
        )
    }
}