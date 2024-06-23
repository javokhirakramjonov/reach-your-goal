package com.javokhir.reachyourgoal.presentation.screen.settings.mvi.state

import com.javokhir.reachyourgoal.presentation.screen.settings.domain.SettingsItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class ScreenUiState(
    val settingsItems: ImmutableList<SettingsItem> = emptyList<SettingsItem>().toImmutableList()
)

