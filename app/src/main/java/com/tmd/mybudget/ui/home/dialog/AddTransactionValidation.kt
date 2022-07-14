package com.tmd.mybudget.ui.home.dialog

sealed class AddTransactionValidation() {
    class TransactionType(): AddTransactionValidation()
    class Description(): AddTransactionValidation()
    class Amount(): AddTransactionValidation()
}