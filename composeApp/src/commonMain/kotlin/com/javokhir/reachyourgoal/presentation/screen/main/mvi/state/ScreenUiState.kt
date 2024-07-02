package com.javokhir.reachyourgoal.presentation.screen.main.mvi.state

import com.javokhir.reachyourgoal.domain.entity.Task
import com.javokhir.reachyourgoal.domain.entity.Week
import com.javokhir.reachyourgoal.domain.enums.TaskStatus
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class ScreenUiState(
    val weeks: ImmutableList<Week> = emptyList<Week>().toImmutableList(),
    val currentWeek: Week? = null,
    val scheduledTasks: ImmutableList<Pair<Task, ImmutableList<TaskStatus>>> =
        emptyList<Pair<Task, ImmutableList<TaskStatus>>>().toImmutableList()
)