package com.toadfrogson.horrorhub.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HorrorApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}