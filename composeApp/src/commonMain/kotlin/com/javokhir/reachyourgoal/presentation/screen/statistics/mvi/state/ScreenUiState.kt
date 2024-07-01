package com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.state

import com.javokhir.reachyourgoal.domain.enums.TaskStatus
import com.javokhir.reachyourgoal.domain.model.DailyProgress
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class ScreenUiState(
    val taskStatusesOfToday: ImmutableList<Pair<TaskStatus, Int>> = emptyList<Pair<TaskStatus, Int>>().toImmutableList(),
    val dailyProgressesOfWeek: ImmutableList<DailyProgress> = emptyList<DailyProgress>().toImmutableList(),
)