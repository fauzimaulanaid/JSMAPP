package com.fauzimaulana.jsmapp

import android.app.Application
import com.fauzimaulana.jsmapp.core.di.networkModule
import com.fauzimaulana.jsmapp.core.di.repositoryModule
import com.fauzimaulana.jsmapp.core.di.useCaseModule
import com.fauzimaulana.jsmapp.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}