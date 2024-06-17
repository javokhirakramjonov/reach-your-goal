package screen.main.mvi.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import domain.entity.Task
import domain.enums.TaskStatus
import kotlinx.collections.immutable.ImmutableList

private val weekNames = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

@Composable
fun WeeklySchedule(
    modifier: Modifier = Modifier,
    scheduledTasks: ImmutableList<Pair<Task, ImmutableList<TaskStatus>>>,
    onScheduleChanged: (day: Int, status: TaskStatus) -> Unit
) {
    LazyColumn(modifier = modifier) {
        header(modifier = Modifier.background(Color.White))

        items(scheduledTasks) {
            TaskRow(
                task = it.first,
                statuses = it.second,
                onScheduleChanged = onScheduleChanged
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
                .border(1.dp, Color.Black)
                .then(modifier)
        ) {
            Box(
                modifier = Modifier
                    .onGloballyPositioned {
                        val height = with(density) { it.size.height.toDp() }
                        maxHeight = max(maxHeight, height)
                    }
                    .weight(1f)
                    .heightIn(min = maxHeight),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(text = "Task name")
            }
            weekNames.forEach {
                Box(
                    modifier = Modifier
                        .height(maxHeight)
                        .width(1.dp)
                        .background(Color.Black)
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
                    Text(text = it)
                }
            }
        }
    }
}