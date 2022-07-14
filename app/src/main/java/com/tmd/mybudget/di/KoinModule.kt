package com.tmd.mybudget.di

import android.app.Application
import androidx.room.Room
import com.tmd.mybudget.database.MyBudgetDatabase
import com.tmd.mybudget.dispatcher.DefaultDispatcherProvider
import com.tmd.mybudget.dispatcher.DispatcherProvider
import com.tmd.mybudget.domain.usecase.AddTransactionUseCase
import com.tmd.mybudget.domain.usecase.DeleteTransactionUseCase
import com.tmd.mybudget.domain.usecase.FetchTransactionUseCase
import com.tmd.mybudget.repository.TransactionRepository
import com.tmd.mybudget.repository.TransactionRepositoryImpl
import com.tmd.mybudget.ui.home.dialog.AddTransactionDialogViewModel
import com.tmd.mybudget.ui.home.HomeViewModel
import com.tmd.mybudget.ui.home.TransactionHistoryAdapter
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val commonModule = module {
    single<DispatcherProvider> { DefaultDispatcherProvider() }
}

val databaseModule = module {
    fun provideDatabase(application: Application): MyBudgetDatabase {
        return Room.databaseBuilder(application, MyBudgetDatabase::class.java, "mybudget")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    single { provideDatabase(androidApplication()) }
}

val homeModule = module {
    single<TransactionRepository> {
        TransactionRepositoryImpl(
            database = get()
        )
    }

    factory {
        AddTransactionUseCase(
            transactionRepository = get()
        )
    }

    factory {
        DeleteTransactionUseCase(
            transactionRepository = get()
        )
    }

    factory {
        FetchTransactionUseCase(
            transactionRepository = get()
        )
    }

    factory {
        TransactionHistoryAdapter()
    }

    viewModel {
        AddTransactionDialogViewModel(
            addTransactionUseCase = get()
        )
    }

    viewModel {
        HomeViewModel(
            fetchTransactionUseCase = get(),
            deleteTransactionUseCase = get(),
        )
    }
}