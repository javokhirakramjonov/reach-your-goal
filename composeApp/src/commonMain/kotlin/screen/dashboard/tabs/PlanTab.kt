package screen.dashboard.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.painterResource
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.ic_calendar
import screen.plans.PlansScreen

object PlanTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "Plan"
            val icon = painterResource(Res.drawable.ic_calendar)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(PlansScreen())
    }


}