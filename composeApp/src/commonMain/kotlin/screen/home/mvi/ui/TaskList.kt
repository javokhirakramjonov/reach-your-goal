package screen.home.mvi.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskList(
    modifier: Modifier = Modifier,
    columns: Int = 2,
    tasks: ImmutableList<Task>,
    onTaskClick: (Task) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(columns),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(tasks) { task ->
            TaskItem(
                modifier = Modifier
                    .animateItemPlacement()
                    .fillMaxWidth()
                    .height(100.dp * columns)
                    .padding(8.dp)
                    .background(Color.DarkGray),
                task = task
            ) {
                onTaskClick(task)
            }
        }
    }
}