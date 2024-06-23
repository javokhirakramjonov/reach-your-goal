package com.javokhir.reachyourgoal.presentation.bottomSheet.themeSelector.mvi.state

import com.javokhir.reachyourgoal.theme.ThemeType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class ScreenUiState(
    val themeTypes: ImmutableList<ThemeType> = ThemeType.entries.toImmutableList(),
    val currentTheme: ThemeType? = null
)