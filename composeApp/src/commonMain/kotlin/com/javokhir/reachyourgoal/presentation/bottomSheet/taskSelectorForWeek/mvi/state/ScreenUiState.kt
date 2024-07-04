package com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForWeek.mvi.state

import com.javokhir.reachyourgoal.domain.model.SelectableTask
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class ScreenUiState(
    val weekId: Int,
    val selectableTasks: ImmutableList<SelectableTask> = emptyList<SelectableTask>().toImmutableList(),
)