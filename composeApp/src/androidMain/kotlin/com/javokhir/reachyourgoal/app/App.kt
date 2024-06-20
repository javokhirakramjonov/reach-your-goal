package com.javokhir.reachyourgoal.app

import android.app.Application
import com.javokhir.reachyourgoal.di.initKoin
import org.koin.android.ext.koin.androidContext

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(applicationContext)
        }
    }
}