package dialog.taskSelectorForPlan.mvi.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dialog.taskSelectorForPlan.mvi.event.ScreenEvent
import dialog.taskSelectorForPlan.mvi.state.ScreenUiState

@Composable
fun ScreenUi(
    modifier: Modifier = Modifier,
    uiState: ScreenUiState,
    action: (ScreenEvent.Input) -> Unit
) {
    Surface(modifier = modifier) {
        LazyColumn {
            items(uiState.selectableTasks, key ={ it.task.id }) {
                SelectableTaskItem(
                    modifier = Modifier.padding(8.dp),
                    selectableTask = it,
                    onClick = {
                        action(
                            ScreenEvent.Input.TaskSelectionChanged(
                                it.task.id,
                                !it.isSelected
                            )
                        )
                    }
                )

                HorizontalDivider()
            }
        }
    }
}