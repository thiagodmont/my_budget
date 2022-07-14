package com.tmd.mybudget.ui.home

import com.tmd.mybudget.base.BaseViewHolder
import com.tmd.mybudget.databinding.ItemTransactionContentBinding
import com.tmd.mybudget.domain.model.TransactionModel
import com.tmd.mybudget.domain.model.TransactionType
import com.tmd.mybudget.utils.MoneyFormat

internal class TransactionContentViewHolder(
    private val viewBinding: ItemTransactionContentBinding,
    private val onClick: TransactionHistoryAdapter.OnItemClickListener?,
): BaseViewHolder(viewBinding) {

    fun binding(data: TransactionModel) {
        viewBinding.tvDescription.text = data.description
        viewBinding.tvAmount.text = when(data.type) {
            TransactionType.Expenses -> "- ${MoneyFormat.format(data.amount)}"
            else -> MoneyFormat.format(data.amount)
        }
        viewBinding.root.setOnClickListener {
            onClick?.onItemClick(data)
        }
    }
}