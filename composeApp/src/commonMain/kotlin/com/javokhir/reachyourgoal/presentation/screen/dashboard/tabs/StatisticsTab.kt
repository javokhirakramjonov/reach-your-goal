package com.javokhir.reachyourgoal.presentation.screen.dashboard.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.javokhir.reachyourgoal.presentation.screen.statistics.StatisticsScreen
import org.jetbrains.compose.resources.stringResource
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.statistics_tab

object StatisticsTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(Res.string.statistics_tab)
            val icon = rememberVectorPainter(Icons.Default.Analytics)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(StatisticsScreen())
    }
}