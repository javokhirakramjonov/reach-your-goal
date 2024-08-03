package com.javokhir.reachyourgoal.presentation.bottomSheet.languageSelector.mvi.event

import com.javokhir.reachyourgoal.locale.Language

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
        data class LanguageSelected(val language: Language) : Input
    }

    sealed interface Command : ScreenEvent
}