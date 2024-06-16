package screen.dashboard.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.jetbrains.compose.resources.painterResource
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.ic_task
import screen.tasks.TasksScreen

object TaskTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "Task"
            val icon = painterResource(Res.drawable.ic_task)

            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(TasksScreen())
    }


}