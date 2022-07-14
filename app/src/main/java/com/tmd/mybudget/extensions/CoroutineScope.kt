package com.tmd.mybudget.extensions

import com.tmd.mybudget.dispatcher.DispatcherProvider
import kotlinx.coroutines.*
import org.koin.core.context.GlobalContext

val CoroutineScope.dispatcherProvider by lazy {
    GlobalContext.get().get<DispatcherProvider>()
}

inline fun CoroutineScope.launchIO(crossinline execution: suspend CoroutineScope.() -> Unit) {
    this.launch(dispatcherProvider.io) { execution() }
}

suspend inline fun <reified T> CoroutineScope.withMain(crossinline execution: suspend CoroutineScope.() -> T): T {
    return withContext(dispatcherProvider.main) { execution() }
}
