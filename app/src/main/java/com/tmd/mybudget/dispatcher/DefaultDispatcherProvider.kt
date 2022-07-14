package com.tmd.mybudget.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class DefaultDispatcherProvider :
    DispatcherProvider {

    override val main: CoroutineDispatcher = Dispatchers.Main
    override val io: CoroutineDispatcher = Dispatchers.IO
    override val newThread: CoroutineDispatcher = Dispatchers.Default
}