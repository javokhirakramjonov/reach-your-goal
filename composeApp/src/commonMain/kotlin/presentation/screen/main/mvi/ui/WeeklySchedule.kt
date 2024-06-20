package presentation.screen.main.mvi.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import domain.entity.Task
import domain.enums.TaskStatus
import domain.model.WeekDays
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.stringResource
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.main_screen_task_name
import reach_your_goal.composeapp.generated.resources.no_tasks
import utils.name

@Composable
fun WeeklySchedule(
    modifier: Modifier = Modifier,
    scheduledTasks: ImmutableList<Pair<Task, ImmutableList<TaskStatus>>>,
    onStatusChanged: (taskId: Int, day: Int, status: TaskStatus) -> Unit
) {
    val headerBackground = MaterialTheme.colorScheme.background

    LazyColumn(modifier = modifier) {
        header(modifier = Modifier.background(headerBackground))

        if(scheduledTasks.isEmpty()) {
            item {
                Box(
                    modifier = Modifier.fillParentMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(Res.string.no_tasks))
                }
            }
        }

        items(scheduledTasks) {(task, statuses) ->
            TaskRow(
                task = task,
                statuses = statuses,
                onStatusChanged = { day, status->
                    onStatusChanged(task.id, day, status)
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun LazyListScope.header(
    modifier: Modifier = Modifier
) {
    stickyHeader {
        val density = LocalDensity.current

        var maxHeight by remember { mutableStateOf(0.dp) }

        Row(
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.primary)
                .then(modifier)
        ) {
            Box(
                modifier = Modifier
                    .onGloballyPositioned {
                        val height = with(density) { it.size.height.toDp() }
                        maxHeight = max(maxHeight, height)
                    }
                    .weight(1f)
                    .heightIn(min = maxHeight)
                    .padding(4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = stringResource(Res.string.main_screen_task_name))
            }
            WeekDays.entries.forEach {
                Box(
                    modifier = Modifier
                        .height(maxHeight)
                        .width(1.dp)
                        .background(MaterialTheme.colorScheme.primary)
                )

                Box(
                    modifier = Modifier
                        .onGloballyPositioned {
                            val height = with(density) { it.size.height.toDp() }
                            maxHeight = max(maxHeight, height)
                        }
                        .weight(0.3f)
                        .heightIn(min = maxHeight),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = it.name())
                }
            }
        }
    }
}