package com.tmd.mybudget.helpers

import android.app.Application
import android.content.Context
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class Resources {

    val application: Application = mockk()

    private val context: Context = mockk(relaxed = true)

    init {
        every { application.applicationContext } returns context
        every { application.baseContext } returns context
    }
}