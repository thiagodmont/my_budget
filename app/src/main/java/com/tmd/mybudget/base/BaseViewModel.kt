package com.tmd.mybudget.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmd.mybudget.extensions.launchIO
import com.tmd.mybudget.extensions.withMain
import kotlinx.coroutines.CoroutineScope

abstract class BaseViewModel : ViewModel() {

    protected inline fun launchIO(crossinline execution: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launchIO(execution)
    }

    protected suspend inline fun <reified T> withMain(crossinline execution: suspend CoroutineScope.() -> T): T {
        return viewModelScope.withMain(execution)
    }
}