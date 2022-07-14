package com.tmd.mybudget.domain.usecase

import com.tmd.mybudget.domain.model.TransactionModelDummy.getListTransactionModelWithBalance

object FetchTransactionUseCaseDummy {

    fun getFinancialData(): FinancialData {
        val data = getListTransactionModelWithBalance()

        return FinancialData(
            transactions = data.list,
            balance = data.balance,
        )
    }
}