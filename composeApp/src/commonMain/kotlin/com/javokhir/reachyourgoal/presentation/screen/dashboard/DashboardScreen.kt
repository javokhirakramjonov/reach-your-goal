package com.javokhir.reachyourgoal.presentation.screen.dashboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.javokhir.reachyourgoal.presentation.screen.dashboard.tabs.MainTab
import com.javokhir.reachyourgoal.presentation.screen.dashboard.tabs.SettingsTab
import com.javokhir.reachyourgoal.presentation.screen.dashboard.tabs.TaskTab
import com.javokhir.reachyourgoal.presentation.screen.dashboard.tabs.WeekTab
import com.javokhir.reachyourgoal.theme.MainAppTheme

class DashboardScreen : Screen {
    @Composable
    override fun Content() {
        TabNavigator(MainTab) {
            MainAppTheme {
                Scaffold(
                    content = {
                        Box(modifier = Modifier.padding(it)) {
                            CurrentTab()
                        }
                    },
                    bottomBar = {
                        NavigationBar {
                            TabNavigationItem(MainTab)
                            TabNavigationItem(WeekTab)
                            TabNavigationItem(TaskTab)
                            TabNavigationItem(SettingsTab)
                        }
                    }
                )
            }
        }
    }

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current

        NavigationBarItem(
            selected = tabNavigator.current == tab,
            onClick = { tabNavigator.current = tab },
            icon = {
                tab.options.icon?.let { painter ->
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painter,
                        contentDescription = tab.options.title
                    )
                }
            },
            label = { Text(tab.options.title) }
        )
    }
}