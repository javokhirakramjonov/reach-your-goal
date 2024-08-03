package com.javokhir.reachyourgoal.presentation.screen.settings.domain

sealed interface SettingsItem {

    sealed interface Clickable : SettingsItem {
        fun onClick()

        class SelectTheme(private val onClick: () -> Unit) : SettingsItem.Clickable {
            override fun onClick() {
                onClick.invoke()
            }
        }
    }

}