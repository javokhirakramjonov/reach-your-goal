package com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.event

sealed interface ScreenEvent {
    sealed interface Input : ScreenEvent {
    }

    sealed interface Command : ScreenEvent {
    }
}