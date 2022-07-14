package com.tmd.mybudget.ui.home

import assertk.assertions.isNotNull
import assertk.assertions.isNull
import com.tmd.mybudget.dispatcher.DispatcherProvider
import com.tmd.mybudget.domain.model.TransactionModelDummy
import com.tmd.mybudget.domain.usecase.AddTransactionUseCase
import com.tmd.mybudget.helpers.BaseViewModelTest
import com.tmd.mybudget.helpers.isFireSingleEvent
import com.tmd.mybudget.helpers.test
import com.tmd.mybudget.helpers.testHistory
import com.tmd.mybudget.ui.home.dialog.AddTransactionDialogViewModel
import com.tmd.mybudget.ui.home.dialog.AddTransactionValidation
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Test
import org.hamcrest.MatcherAssert
import org.koin.core.context.GlobalContext

@ExperimentalCoroutinesApi
class AddTransactionDialogViewModelTest: BaseViewModelTest() {

    private val addTransactionUseCase = mockk<AddTransactionUseCase>()

    private val viewModel: AddTransactionDialogViewModel by lazy {
        AddTransactionDialogViewModel(addTransactionUseCase)
    }

    override fun setup() {
        val dispatcherTest = GlobalContext.get().get<DispatcherProvider>()
        Dispatchers.setMain(dispatcherTest.main)
    }

    @Test
    fun `when save is called verify requirement and add a transaction with success`() = runTest {
        coEvery { addTransactionUseCase.run(params = any()) } returns Unit

        viewModel.setTransactionType(TransactionModelDummy.DUMMY_TYPE)
        viewModel.setTransactionDescription(TransactionModelDummy.DUMMY_DESCRIPTION)
        viewModel.setTransactionAmount(TransactionModelDummy.DUMMY_AMOUNT)

        val submit = viewModel.submit.testHistory()

        viewModel.save()
        advanceUntilIdle()

        submit.isFireSingleEvent()
    }

    @Test
    fun `when save is called and the transaction type is missed`() = runTest {
        coEvery { addTransactionUseCase.run(params = any()) } returns Unit

        viewModel.setTransactionDescription(TransactionModelDummy.DUMMY_DESCRIPTION)
        viewModel.setTransactionAmount(TransactionModelDummy.DUMMY_AMOUNT)

        viewModel.save()
        advanceUntilIdle()

        viewModel.validation.test().isNotNull()
        viewModel.submit.test().isNull()
        MatcherAssert.assertThat(viewModel.validation.value, instanceOf(AddTransactionValidation.TransactionType::class.java))
    }

    @Test
    fun `when save is called and the description is missed`() = runTest {
        coEvery { addTransactionUseCase.run(params = any()) } returns Unit

        viewModel.setTransactionType(TransactionModelDummy.DUMMY_TYPE)
        viewModel.setTransactionAmount(TransactionModelDummy.DUMMY_AMOUNT)

        viewModel.save()
        advanceUntilIdle()

        viewModel.validation.test().isNotNull()
        viewModel.submit.test().isNull()
        MatcherAssert.assertThat(viewModel.validation.value, instanceOf(AddTransactionValidation.Description::class.java))
    }

    @Test
    fun `when save is called and the amount is missed`() = runTest {
        coEvery { addTransactionUseCase.run(params = any()) } returns Unit

        viewModel.setTransactionType(TransactionModelDummy.DUMMY_TYPE)
        viewModel.setTransactionDescription(TransactionModelDummy.DUMMY_DESCRIPTION)

        viewModel.save()
        advanceUntilIdle()

        viewModel.validation.test().isNotNull()
        viewModel.submit.test().isNull()
        MatcherAssert.assertThat(viewModel.validation.value, instanceOf(AddTransactionValidation.Amount::class.java))
    }
}