package com.javokhir.reachyourgoal.presentation.screen.tasks.mvi.state

import com.javokhir.reachyourgoal.domain.entity.Task
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class ScreenUiState(
    val tasks: ImmutableList<Task> = emptyList<Task>().toImmutableList(),
)