package com.tmd.mybudget.ui.home

import com.tmd.mybudget.domain.model.TransactionModel
import com.tmd.mybudget.domain.model.TransactionModelDummy
import com.tmd.mybudget.domain.model.TransactionType

object FinancialDigestDummy {

    fun getList(vararg typeAndAmount: Pair<TransactionType, Double>): List<TransactionModel> {
        return typeAndAmount.map {
            TransactionModelDummy.getTransactionModel(
                type = it.first,
                amount = it.second,
            )
        }
    }
}