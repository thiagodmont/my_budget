package com.tmd.mybudget.ui.home

import com.tmd.mybudget.base.BaseViewHolder
import com.tmd.mybudget.databinding.ItemTransactionHeaderDateBinding

internal class TransactionHeaderDateViewHolder(private val viewBinding: ItemTransactionHeaderDateBinding):
    BaseViewHolder(viewBinding) {

    fun binding(date: String) {
        viewBinding.tvDate.text = date
    }
}