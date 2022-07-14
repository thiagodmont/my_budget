package com.tmd.mybudget.domain.usecase

import com.tmd.mybudget.base.BaseUseCase
import com.tmd.mybudget.domain.model.TransactionModel
import com.tmd.mybudget.repository.TransactionRepository
import timber.log.Timber

internal class AddTransactionUseCase(
    private val transactionRepository: TransactionRepository
): BaseUseCase<TransactionModel, Unit> {

    override suspend fun run(params: TransactionModel?): Unit = try {
        requireNotNull(params) {
            "Failed to execute AddTransactionUseCase. Params must not be null."
        }

        transactionRepository.addTransaction(params)
    } catch (e: Exception) {
        throw AddTransactionUseCaseException(e.message, e)
            .also { Timber.e(e, "Failed to add transaction AddTransactionUseCase.") }
    }

    class AddTransactionUseCaseException @JvmOverloads constructor(
        message: String? = null,
        throwable: Throwable? = null
    ) : Exception(message, throwable)
}