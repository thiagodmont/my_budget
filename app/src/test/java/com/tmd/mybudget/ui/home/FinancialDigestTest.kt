package com.tmd.mybudget.ui.home

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test
import com.tmd.mybudget.domain.model.TransactionType
import com.tmd.mybudget.ui.home.dialog.AddTransactionValidation
import com.tmd.mybudget.utils.DateFormat
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert
import java.util.*

class FinancialDigestTest {

    @Test
    fun `when pass a list of TransactionModel return FinancialBalance with success`() {
        val dummyData = FinancialDigestDummy.getList(
            Pair(TransactionType.Income, 70.00),
            Pair(TransactionType.Income, 30.00),
            Pair(TransactionType.Expenses, 10.00),
            Pair(TransactionType.Expenses, 20.00),
        )

        val result = FinancialDigest.getBalance(data = dummyData)

        assertThat(result.balance).isEqualTo(70.00)
        assertThat(result.income).isEqualTo(100.00)
        assertThat(result.expenses).isEqualTo(30.00)
        assertThat(result.ratio).isEqualTo(30)
    }

    @Test
    fun `when pass a list of TransactionModel return list of TransactionListItem with success`() {
        val dummyData = FinancialDigestDummy.getList(
            Pair(TransactionType.Income, 70.00),
            Pair(TransactionType.Expenses, 10.00),
        )

        val result = FinancialDigest.convertTransactionListItem(data = dummyData)

        MatcherAssert.assertThat(result[0],
            instanceOf(TransactionListItem.TransactionHeaderDate::class.java)
        )

        MatcherAssert.assertThat(result[1],
            instanceOf(TransactionListItem.TransactionContent::class.java)
        )

        MatcherAssert.assertThat(result[2],
            instanceOf(TransactionListItem.TransactionContentLast::class.java)
        )
    }
}