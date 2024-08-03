package com.javokhir.reachyourgoal.presentation.screen.dashboard.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.javokhir.reachyourgoal.AppLocale
import com.javokhir.reachyourgoal.presentation.screen.weeks.WeeksScreen
import org.jetbrains.compose.resources.painterResource
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.ic_calendar

object WeekTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = AppLocale.current.tabs.week
            val icon = painterResource(Res.drawable.ic_calendar)

            return remember(title, icon) {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(WeeksScreen())
    }


}