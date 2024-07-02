package com.javokhir.reachyourgoal.presentation.screen.week.mvi.state

import com.javokhir.reachyourgoal.domain.entity.Task
import com.javokhir.reachyourgoal.domain.entity.Week
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class ScreenUiState(
    val week: Week,
    val tasks: ImmutableList<Task> = emptyList<Task>().toImmutableList()
)