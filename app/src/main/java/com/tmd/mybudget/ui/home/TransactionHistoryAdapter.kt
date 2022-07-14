package com.tmd.mybudget.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tmd.mybudget.R
import com.tmd.mybudget.base.BaseViewHolder
import com.tmd.mybudget.databinding.ItemTransactionContentBinding
import com.tmd.mybudget.databinding.ItemTransactionContentLastBinding
import com.tmd.mybudget.databinding.ItemTransactionHeaderDateBinding
import com.tmd.mybudget.domain.model.TransactionModel
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

internal class TransactionHistoryAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(model: TransactionModel)
    }

    private var onClick: OnItemClickListener? = null

    fun setOnItemClickListener(clickListener: OnItemClickListener) {
        this.onClick = clickListener
    }

    private var data: List<TransactionListItem> = emptyList()
        set(value) {
            val result = DiffUtil.calculateDiff(TransactionHistoryDiffCallback(field, value))
            field = value
            result.dispatchUpdatesTo(this)
        }

    fun populate(data: List<TransactionModel>) {
        this.data = FinancialDigest.convertTransactionListItem(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            R.layout.item_transaction_header_date -> {
                TransactionHeaderDateViewHolder(
                    ItemTransactionHeaderDateBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
            R.layout.item_transaction_content -> {
                TransactionContentViewHolder(
                    ItemTransactionContentBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    ),
                    onClick
                )
            }
            R.layout.item_transaction_content_last -> {
                TransactionContentLastViewHolder(
                    ItemTransactionContentLastBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    ),
                    onClick
                )
            }
            else -> throw IllegalArgumentException("Unexpected viewType received: $viewType.")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when(holder) {
            is TransactionHeaderDateViewHolder -> {
                val item = data[position] as TransactionListItem.TransactionHeaderDate
                holder.binding(item.groupDate)
            }
            is TransactionContentViewHolder -> {
                val item = data[position] as TransactionListItem.TransactionContent
                holder.binding(item.transaction)
            }
            is TransactionContentLastViewHolder -> {
                val item = data[position] as TransactionListItem.TransactionContentLast
                holder.binding(item.transaction)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int = data[position].resource

    private class TransactionHistoryDiffCallback(
        private val oldData: List<TransactionListItem>,
        private val newData: List<TransactionListItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldData.size

        override fun getNewListSize(): Int = newData.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldData[oldItemPosition]
            var newItem = newData[newItemPosition]

            if (oldItem.resource != newItem.resource) {
                return false
            }

            return when(oldItem) {
                is TransactionListItem.TransactionHeaderDate -> {
                    newItem = newData[newItemPosition] as TransactionListItem.TransactionHeaderDate
                    oldItem.groupDate == newItem.groupDate
                }
                is TransactionListItem.TransactionContent -> {
                    newItem = newData[newItemPosition] as TransactionListItem.TransactionContent
                    oldItem.transaction._id == newItem.transaction._id
                }
                is TransactionListItem.TransactionContentLast -> {
                    newItem = newData[newItemPosition] as TransactionListItem.TransactionContentLast
                    oldItem.transaction._id == newItem.transaction._id
                }
            }
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData[oldItemPosition] == newData[newItemPosition]
        }
    }
}