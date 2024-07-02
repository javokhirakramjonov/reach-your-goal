package com.javokhir.reachyourgoal.presentation.screen.weeks.mvi.state

import com.javokhir.reachyourgoal.domain.entity.Week
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class ScreenUiState(
    val weeks: ImmutableList<Week> = emptyList<Week>().toImmutableList()
)