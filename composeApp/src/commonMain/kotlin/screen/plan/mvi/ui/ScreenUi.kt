package screen.plan.mvi.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import screen.plan.mvi.event.ScreenEvent
import screen.plan.mvi.state.ScreenUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenUi(
    modifier: Modifier = Modifier,
    uiState: ScreenUiState,
    action: (ScreenEvent.Input) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = uiState.plan.name)
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Text(text = uiState.plan.name)

            Text(text = uiState.plan.description.orEmpty())

            Button(onClick = { action(ScreenEvent.Input.UpdateTasksClicked) }) {
                Text(text = "Update tasks")
            }

            LazyColumn(modifier.fillMaxSize()) {
                items(uiState.tasks, key = { it.id }) { task ->
                    Text(text = task.name)
                }
            }
        }
    }
}