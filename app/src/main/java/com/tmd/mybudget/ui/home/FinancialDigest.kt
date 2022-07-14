package com.tmd.mybudget.ui.home

import com.tmd.mybudget.domain.model.TransactionModel
import com.tmd.mybudget.domain.model.TransactionType
import com.tmd.mybudget.utils.DateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class FinancialBalance(
    val income: Double,
    val expenses: Double,
    val balance: Double,
    val ratio: Int,
)

object FinancialDigest {

    fun getBalance(data: List<TransactionModel>): FinancialBalance {
        var income = 0.0
        var expenses = 0.0

        data.forEach {
            when(it.type) {
                TransactionType.Income -> income += it.amount
                TransactionType.Expenses -> expenses += it.amount
            }
        }

        val ratio = (100 * expenses) / income

        return FinancialBalance(
            income = income,
            expenses = expenses,
            balance = income - expenses,
            ratio = ratio.toInt()
        )
    }

    fun convertTransactionListItem(data: List<TransactionModel>): List<TransactionListItem> {
        val grouped = data.groupBy {
            DateFormat.friendly(it.createdAt)
        }

        val list = grouped.map {
            val list = mutableListOf<TransactionListItem>(
                TransactionListItem.TransactionHeaderDate(it.key)
            )

            it.value.forEachIndexed { index, data ->

                if (index < it.value.lastIndex) {
                    list.add(
                        TransactionListItem.TransactionContent(data)
                    )
                } else {
                    list.add(
                        TransactionListItem.TransactionContentLast(data)
                    )
                }
            }

            list.toList()
        }

        return list.flatten()
    }
}