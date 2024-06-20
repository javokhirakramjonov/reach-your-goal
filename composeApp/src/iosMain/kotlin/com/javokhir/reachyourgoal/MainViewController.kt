package com.javokhir.reachyourgoal

import App
import androidx.compose.ui.window.ComposeUIViewController
import com.javokhir.reachyourgoal.di.iosModules
import di.sharedModules
import org.koin.core.context.startKoin


fun MainViewController() = ComposeUIViewController {
    initKoin()

    App()
}

private fun initKoin() {
    startKoin {
        modules(sharedModules() + iosModules())
    }
}