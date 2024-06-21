package com.javokhir.reachyourgoal.presentation.screen.main.mvi.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.javokhir.reachyourgoal.domain.entity.Task
import com.javokhir.reachyourgoal.domain.enums.TaskStatus
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.stringResource
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.no_tasks

@Composable
fun WeeklySchedule(
    modifier: Modifier = Modifier,
    scheduledTasks: ImmutableList<Pair<Task, ImmutableList<TaskStatus>>>,
    onStatusChanged: (taskId: Int, day: Int, status: TaskStatus) -> Unit
) {
    val headerBackground = MaterialTheme.colorScheme.background

    LazyColumn(modifier = modifier) {
        header(
            modifier = Modifier.background(headerBackground),
        )

        if (scheduledTasks.isEmpty()) {
            item {
                Box(
                    modifier = Modifier.fillParentMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(Res.string.no_tasks))
                }
            }
        }

        items(scheduledTasks) { (task, statuses) ->
            TaskRow(
                task = task,
                statuses = statuses,
                onStatusChanged = { day, status ->
                    onStatusChanged(task.id, day, status)
                },
            )
        }
    }
}