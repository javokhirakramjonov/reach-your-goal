package com.javokhir.reachyourgoal.presentation.screen.dashboard.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.javokhir.reachyourgoal.presentation.screen.main.MainScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.ic_status
import reach_your_goal.composeapp.generated.resources.main_tab


object MainTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(Res.string.main_tab)
            val icon = painterResource(Res.drawable.ic_status)

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
        Navigator(MainScreen())
    }


}