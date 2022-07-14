package com.tmd.mybudget.domain.usecase

import com.tmd.mybudget.base.BaseUseCase
import com.tmd.mybudget.domain.model.TransactionModel
import com.tmd.mybudget.repository.TransactionRepository
import com.tmd.mybudget.ui.home.FinancialBalance
import com.tmd.mybudget.ui.home.FinancialDigest
import timber.log.Timber

data class FinancialData(
    val balance: FinancialBalance,
    val transactions: List<TransactionModel>
)

internal class FetchTransactionUseCase(
    private val transactionRepository: TransactionRepository
): BaseUseCase<Unit, FinancialData> {

    override suspend fun run(params: Unit?): FinancialData = try {
        val data = transactionRepository.getTransactions()

        FinancialData(
            balance = FinancialDigest.getBalance(data),
            transactions = data,
        )
    } catch (e: Exception) {
        throw FetchTransactionUseCaseException(e.message, e)
            .also { Timber.e(e, "Failed to fetch transactions FetchTransactionUseCase.") }
    }

    class FetchTransactionUseCaseException @JvmOverloads constructor(
        message: String? = null,
        throwable: Throwable? = null
    ) : Exception(message, throwable)
}