package com.javokhir.reachyourgoal.presentation.screen.settings.domain

sealed interface SettingsItem {

    sealed interface Clickable : SettingsItem {
        fun onClick()

        class SelectTheme(private val onClick: () -> Unit) : Clickable {
            override fun onClick() {
                onClick.invoke()
            }
        }

        class SelectLanguage(private val onClick: () -> Unit) : Clickable {
            override fun onClick() {
                onClick.invoke()
            }
        }
    }

}