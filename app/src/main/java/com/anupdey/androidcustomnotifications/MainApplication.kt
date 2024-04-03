package com.anupdey.androidcustomnotifications

import android.app.Application
import com.anupdey.androidcustomnotifications.util.createNotificationChannel
import timber.log.Timber

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}