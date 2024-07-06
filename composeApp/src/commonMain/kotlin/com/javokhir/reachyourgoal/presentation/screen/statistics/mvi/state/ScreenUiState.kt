package com.javokhir.reachyourgoal.presentation.screen.statistics.mvi.state

import com.javokhir.reachyourgoal.domain.model.DailyTaskProgress
import com.javokhir.reachyourgoal.domain.model.WeekDayTaskProgress
import com.javokhir.reachyourgoal.domain.model.WeeklyTaskProgress
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class ScreenUiState(
    val taskStatusesOfToday: ImmutableList<WeekDayTaskProgress> = emptyList<WeekDayTaskProgress>().toImmutableList(),
    val dailyTaskProgressesOfWeek: ImmutableList<DailyTaskProgress> = emptyList<DailyTaskProgress>().toImmutableList(),
    val weeklyProgresses: ImmutableList<WeeklyTaskProgress> = emptyList<WeeklyTaskProgress>().toImmutableList()
)