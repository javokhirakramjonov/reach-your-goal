package com.javokhir.reachyourgoal.presentation.screen.main.mvi.state

import com.javokhir.reachyourgoal.domain.entity.Week
import com.javokhir.reachyourgoal.presentation.screen.main.model.TaskAndStates
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class ScreenUiState(
    val weeks: ImmutableList<Week> = emptyList<Week>().toImmutableList(),
    val currentWeek: Week? = null,
    val scheduledTasks: ImmutableList<TaskAndStates> = emptyList<TaskAndStates>().toImmutableList(),
    val isWeekInFuture: Boolean = true,
)