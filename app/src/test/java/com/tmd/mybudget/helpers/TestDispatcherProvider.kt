package com.tmd.mybudget.helpers

import com.tmd.mybudget.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@ExperimentalCoroutinesApi
internal class TestDispatcherProvider : DispatcherProvider {
    override val main: CoroutineDispatcher = UnconfinedTestDispatcher()
    override val io: CoroutineDispatcher = UnconfinedTestDispatcher()
    override val newThread: CoroutineDispatcher = UnconfinedTestDispatcher()
}