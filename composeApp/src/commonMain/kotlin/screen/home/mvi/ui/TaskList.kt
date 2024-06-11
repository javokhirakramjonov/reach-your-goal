package screen.home.mvi.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import domain.Task
import kotlinx.collections.immutable.ImmutableList

@Composable
fun TaskList(
    modifier: Modifier = Modifier,
    tasks: ImmutableList<Task>,
    onTaskClick: (Task) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2)
    ) {
        items(tasks) { task ->
            TaskItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp)
                    .background(Color.DarkGray),
                task = task
            ) {
                onTaskClick(task)
            }
        }
    }
}