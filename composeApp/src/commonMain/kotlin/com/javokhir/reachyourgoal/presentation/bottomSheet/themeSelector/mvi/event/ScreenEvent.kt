package com.javokhir.reachyourgoal.presentation.bottomSheet.themeSelector.mvi.event

import com.javokhir.reachyourgoal.theme.ThemeType

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
        data class ThemeTypeSelected(val themeType: ThemeType) : Input
    }

    sealed interface Command : ScreenEvent
}