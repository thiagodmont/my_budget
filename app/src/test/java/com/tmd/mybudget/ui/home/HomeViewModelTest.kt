package com.tmd.mybudget.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import com.tmd.mybudget.base.ViewState
import com.tmd.mybudget.dispatcher.DispatcherProvider
import com.tmd.mybudget.domain.usecase.DeleteTransactionUseCase
import com.tmd.mybudget.domain.usecase.FetchTransactionUseCase
import com.tmd.mybudget.domain.usecase.FetchTransactionUseCaseDummy
import com.tmd.mybudget.helpers.BaseViewModelTest
import com.tmd.mybudget.helpers.commonModule
import com.tmd.mybudget.helpers.test
import com.tmd.mybudget.helpers.testHistory
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

@ExperimentalCoroutinesApi
class HomeViewModelTest: BaseViewModelTest() {

    private val fetchTransactionUseCase = mockk<FetchTransactionUseCase>()
    private val deleteTransactionUseCase = mockk<DeleteTransactionUseCase>()

    private val viewModel: HomeViewModel by lazy {
        HomeViewModel(fetchTransactionUseCase, deleteTransactionUseCase)
    }

    override fun setup() {
        val dispatcherTest = GlobalContext.get().get<DispatcherProvider>()
        Dispatchers.setMain(dispatcherTest.main)
    }

    @Test
    fun `when fetchTransactionUseCase returns a list ViewState will be set as LOADED`() = runTest {
        coEvery { fetchTransactionUseCase.run() } returns FetchTransactionUseCaseDummy.getFinancialData()
        val historyViewState = viewModel.viewState.testHistory()

        viewModel.fetch()
        advanceUntilIdle()

        historyViewState.isEqualTo(
            listOf(ViewState.EMPTY, ViewState.LOADING, ViewState.LOADED)
        )

        viewModel.transactions.test().isNotNull()
    }

    @After
    fun stoppingKoin() {
        stopKoin()
    }

    override fun tearDown() {
        Dispatchers.resetMain()
    }
}