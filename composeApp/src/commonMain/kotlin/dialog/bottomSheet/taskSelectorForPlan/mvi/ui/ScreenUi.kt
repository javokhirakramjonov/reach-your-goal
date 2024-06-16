package dialog.bottomSheet.taskSelectorForPlan.mvi.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dialog.bottomSheet.taskSelectorForPlan.mvi.event.ScreenEvent
import dialog.bottomSheet.taskSelectorForPlan.mvi.state.ScreenUiState

@Composable
fun ScreenUi(
    modifier: Modifier = Modifier,
    uiState: ScreenUiState,
    action: (ScreenEvent.Input) -> Unit
) {
    Surface(modifier = modifier) {
        Column {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = { action(ScreenEvent.Input.TasksChanged) }
            ) {
                Text("Save")
            }

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(uiState.selectableTasks, key = { it.task.id }) {
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
}