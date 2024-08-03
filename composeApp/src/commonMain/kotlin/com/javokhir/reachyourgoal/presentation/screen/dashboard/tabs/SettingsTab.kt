package com.javokhir.reachyourgoal.presentation.screen.dashboard.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.javokhir.reachyourgoal.AppLocale
import com.javokhir.reachyourgoal.presentation.screen.settings.SettingsScreen


object SettingsTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = AppLocale.current.tabs.settings
            val icon = rememberVectorPainter(Icons.Default.Settings)

            return remember(title, icon) {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(SettingsScreen())
    }


}