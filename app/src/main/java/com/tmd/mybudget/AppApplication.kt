package com.tmd.mybudget

import android.app.Application
import com.tmd.mybudget.di.commonModule
import com.tmd.mybudget.di.databaseModule
import com.tmd.mybudget.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(
                commonModule,
                databaseModule,
                homeModule
            )
        }
    }
}