package com.toadfrogson.horrorhub.application

import android.app.Application
import com.toadfrogson.horrorhub.data.di.dataModule
import com.toadfrogson.horrorhub.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class HorrorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@HorrorApp)
            modules(dataModule, viewModelModule)
        }
    }
}