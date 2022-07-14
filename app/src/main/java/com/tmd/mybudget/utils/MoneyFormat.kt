package com.tmd.mybudget.utils

import java.text.NumberFormat
import java.util.*

object MoneyFormat {

    fun format(amount: Double): String {
        return "$${NumberFormat.getNumberInstance(Locale.US).format(amount)}"
    }
}