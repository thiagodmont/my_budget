package com.tmd.mybudget.helpers

import com.tmd.mybudget.dispatcher.DispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val commonModule = module {
    single<DispatcherProvider> { TestDispatcherProvider() }
}