package com.example.testapp

import android.app.Application
import com.example.testapp.di.remoteModule
import com.example.testapp.di.usecaseModule
import com.example.testapp.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(viewModelsModule, usecaseModule, remoteModule)
        }
    }
}