package com.javokhir.reachyourgoal.presentation.bottomSheet.languageSelector.mvi.state

import com.javokhir.reachyourgoal.locale.Language
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class ScreenUiState(
    val themeTypes: ImmutableList<Language> = Language.entries.toImmutableList(),
    val currentLanguage: Language? = null
)