package com.tmd.mybudget.domain.usecase

import com.tmd.mybudget.domain.model.TransactionModelDummy
import com.tmd.mybudget.helpers.BaseViewModelTest
import com.tmd.mybudget.repository.TransactionRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class FetchTransactionUseCaseTest: BaseViewModelTest() {

    private lateinit var fetchTransactionUseCase: FetchTransactionUseCase

    private val repository = mockk<TransactionRepository>()

    override fun setup() {
        fetchTransactionUseCase = FetchTransactionUseCase(repository)
    }

    @Test
    fun `when fetch transaction returns a list of transaction and balance`() = runBlocking {
        val dummy = TransactionModelDummy.getListTransactionModelWithBalance()
        coEvery { repository.getTransactions() } returns dummy.list

        val result = fetchTransactionUseCase.run()

        Assert.assertTrue(result.transactions.isNotEmpty())
        Assert.assertTrue(result.balance.expenses.equals(dummy.balance.expenses))
        Assert.assertTrue(result.balance.income.equals(dummy.balance.income))
        Assert.assertTrue(result.balance.balance.equals(dummy.balance.balance))
    }
}