package com.tmd.mybudget.domain.usecase

import com.tmd.mybudget.base.BaseUseCase
import com.tmd.mybudget.repository.TransactionRepository
import timber.log.Timber

internal class DeleteTransactionUseCase(
    private val transactionRepository: TransactionRepository
): BaseUseCase<DeleteTransactionUseCase.DeleteTransactionParams, Unit> {

    override suspend fun run(params: DeleteTransactionParams?): Unit = try {
        requireNotNull(params) {
            "Failed to execute DeleteTransactionUseCase. Params must not be null."
        }

        transactionRepository.deleteTransaction(id = params.id)
    } catch (e: Exception) {
        throw DeleteTransactionUseCaseException(e.message, e)
            .also { Timber.e(e, "Failed to delete transaction DeleteTransactionUseCase.") }
    }

    data class DeleteTransactionParams(
        val id: String
    )

    class DeleteTransactionUseCaseException @JvmOverloads constructor(
        message: String? = null,
        throwable: Throwable? = null
    ) : Exception(message, throwable)
}