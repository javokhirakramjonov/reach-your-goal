package com.javokhir.reachyourgoal.presentation.screen.plan.mvi.state

import com.javokhir.reachyourgoal.domain.entity.Plan
import com.javokhir.reachyourgoal.domain.entity.Task
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class ScreenUiState(
    val plan: Plan,
    val tasks: ImmutableList<Task> = emptyList<Task>().toImmutableList()
)