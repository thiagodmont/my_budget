package com.tmd.mybudget.utils

import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

object DateFormat {

    fun friendly(date: Date): String {
        val localDate: LocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("E, dd MMM, yyyy")

        return localDate.format(formatter)
    }
}