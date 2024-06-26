package com.javokhir.reachyourgoal.presentation.screen.plans.mvi.state

import com.javokhir.reachyourgoal.domain.entity.Plan
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class ScreenUiState(
    val plans: ImmutableList<Plan> = emptyList<Plan>().toImmutableList()
)