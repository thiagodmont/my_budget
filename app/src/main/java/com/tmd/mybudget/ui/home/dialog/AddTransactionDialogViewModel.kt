package com.tmd.mybudget.ui.home.dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import com.tmd.mybudget.base.BaseViewModel
import com.tmd.mybudget.base.SingleLiveEvent
import com.tmd.mybudget.base.fire
import com.tmd.mybudget.domain.model.TransactionModel
import com.tmd.mybudget.domain.model.toModelTransactionType
import com.tmd.mybudget.domain.usecase.AddTransactionUseCase
import com.tmd.mybudget.extensions.toLiveData
import com.tmd.mybudget.extensions.withMain
import java.util.*

internal class AddTransactionDialogViewModel(
    private val addTransactionUseCase: AddTransactionUseCase
) : BaseViewModel() {
    private val _transactionType = MutableLiveData<String>()
    val transactionType = _transactionType.toLiveData()

    private val _transactionDescription = MutableLiveData<String>()
    val transactionDescription = _transactionDescription.toLiveData()

    private val _transactionAmount = MutableLiveData<String>()
    val transactionAmount = _transactionAmount.toLiveData()

    private val _validation = MutableLiveData<AddTransactionValidation>()
    val validation = _validation.distinctUntilChanged()

    private val _submit = SingleLiveEvent<Unit>()
    val submit = _submit.toLiveData()

    fun setTransactionType (value: String) {
        _transactionType.postValue(value)
    }

    fun setTransactionDescription (value: String) {
        _transactionDescription.postValue(value)
    }

    fun setTransactionAmount (value: String) {
        _transactionAmount.postValue(value)
    }

    fun save() {
        if (_transactionType.value.isNullOrEmpty()) {
            _validation.value = AddTransactionValidation.TransactionType()
            return
        }

        if (_transactionDescription.value.isNullOrEmpty()) {
            _validation.value = AddTransactionValidation.Description()
            return
        }

        if (_transactionAmount.value.isNullOrEmpty()) {
            _validation.value = AddTransactionValidation.Amount()
            return
        }

        val transaction = TransactionModel(
            _id = UUID.randomUUID().toString(),
            type = _transactionType.value.toString().toModelTransactionType(),
            description = _transactionDescription.value.toString(),
            amount = _transactionAmount.value?.toDouble() ?: 0.0,
            createdAt = Date(),
        )

        launchIO {
            addTransactionUseCase.run(transaction)

            withMain { _submit.fire() }
        }
    }
}