package com.tmd.mybudget.helpers

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

@ExperimentalCoroutinesApi
abstract class BaseViewModelTest {
    open lateinit var application: Application

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun preSetup() {
        application = Resources().application

        startKoin {
            modules(
                module {
                    androidContext(application)
                },
                commonModule
            )
        }

        setup()
    }

    open fun setup() { /* unused */ }

    @After
    fun preTearDown() {
        stopKoin()

        tearDown()
    }

    open fun tearDown() { /* unused */ }
}