package com.tmd.mybudget.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import com.tmd.mybudget.base.BaseViewModel
import com.tmd.mybudget.base.ViewState
import com.tmd.mybudget.domain.model.TransactionModel
import com.tmd.mybudget.domain.usecase.DeleteTransactionUseCase
import com.tmd.mybudget.domain.usecase.FetchTransactionUseCase
import com.tmd.mybudget.extensions.toLiveData

internal class HomeViewModel(
    private val fetchTransactionUseCase: FetchTransactionUseCase,
    private val deleteTransactionUseCase: DeleteTransactionUseCase,
) : BaseViewModel() {

    private val _transactions = MutableLiveData<List<TransactionModel>>()
    val transactions = _transactions.toLiveData()

    private val _balance = MutableLiveData<FinancialBalance>()
    val balance = _balance.toLiveData()

    private val _viewState = MutableLiveData<ViewState>(ViewState.INITIAL)
    val viewState = _viewState.distinctUntilChanged()

    fun fetch() {
        launchIO {
            _viewState.postValue(ViewState.LOADING)

            val viewState = runCatching {
                val data = fetchTransactionUseCase.run()
                _transactions.postValue(data.transactions)
                _balance.postValue(data.balance)

                ViewState.LOADED
            }.recoverCatching {
                ViewState.ERROR
            }.getOrDefault(ViewState.INITIAL)

            _viewState.postValue(viewState)
        }
    }

    fun delete(id: String) {
        launchIO {
            deleteTransactionUseCase.run(
                DeleteTransactionUseCase.DeleteTransactionParams(
                    id = id
                )
            )

            fetch()
        }
    }
}