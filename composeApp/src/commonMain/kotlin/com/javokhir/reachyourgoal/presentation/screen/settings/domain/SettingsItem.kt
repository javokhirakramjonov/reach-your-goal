package com.javokhir.reachyourgoal.presentation.screen.settings.domain

import org.jetbrains.compose.resources.StringResource

sealed interface SettingsItem {

    data class Clickable(
        val titleResource: StringResource,
        val onClick: () -> Unit
    ) : SettingsItem

}