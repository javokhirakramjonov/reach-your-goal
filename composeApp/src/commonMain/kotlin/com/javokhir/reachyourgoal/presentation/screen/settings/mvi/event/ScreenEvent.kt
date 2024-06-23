package com.javokhir.reachyourgoal.presentation.screen.settings.mvi.event

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
    }

    sealed interface Command : ScreenEvent {
        data object OpenThemeSettings : Command
        data object OpenLanguageSettings : Command
    }
}