package com.tmd.mybudget.domain.model

import com.tmd.mybudget.ui.home.FinancialBalance
import com.tmd.mybudget.ui.home.FinancialDigest
import java.util.*

data class ListWithAmount(
    val list: List<TransactionModel>,
    val balance: FinancialBalance
)

object TransactionModelDummy {
    const val DUMMY_TYPE = "Income"
    const val DUMMY_DESCRIPTION = "DUMMY_DESCRIPTION"
    const val DUMMY_AMOUNT = "0.0"

    fun getListTransactionModelWithBalance(times: Int = 5): ListWithAmount = run {
        val list = mutableListOf<TransactionModel>()

        repeat(times) { index ->
            list.add(
                getTransactionModel(
                    amount = index.toDouble(),
                    type = when (index % 2) {
                        0 -> TransactionType.Income
                        else -> TransactionType.Expenses
                    }
                )
            )
        }

        return ListWithAmount(list = list, balance = FinancialDigest.getBalance(list))
    }

    fun getTransactionModel(
        amount: Double = DUMMY_AMOUNT.toDouble(),
        type: TransactionType = TransactionType.Income
    ) = TransactionModel(
            _id = UUID.randomUUID().toString(),
            type = type,
            description = DUMMY_DESCRIPTION,
            amount = amount,
            status = TransactionStatus.Active,
            createdAt = Date()
        )
}