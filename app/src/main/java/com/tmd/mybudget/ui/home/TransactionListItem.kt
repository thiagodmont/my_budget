package com.tmd.mybudget.ui.home

import com.tmd.mybudget.R
import com.tmd.mybudget.domain.model.TransactionModel

sealed class TransactionListItem(val resource: Int) {
    class TransactionHeaderDate(val groupDate: String): TransactionListItem(R.layout.item_transaction_header_date)
    class TransactionContent(val transaction: TransactionModel): TransactionListItem(R.layout.item_transaction_content)
    class TransactionContentLast(val transaction: TransactionModel): TransactionListItem(R.layout.item_transaction_content_last)
}