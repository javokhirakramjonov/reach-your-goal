package com.javokhir.reachyourgoal.presentation.screen.week.domain

sealed interface ScreenError {
    data class NoTasksAvailable(val text: String) : ScreenError
}