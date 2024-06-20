package com.javokhir.reachyourgoal

import App
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.compositionLocalOf
import com.javokhir.reachyourgoal.di.androidModules
import di.sharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.Locale

val LocalAppLocale = compositionLocalOf { Locale.getDefault() }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initKoin(applicationContext)

        setContent {
            App()
        }
    }
}

private fun initKoin(
    context: Context
) {
    startKoin {
        androidContext(context)
        modules(sharedModules() + androidModules())
    }
}