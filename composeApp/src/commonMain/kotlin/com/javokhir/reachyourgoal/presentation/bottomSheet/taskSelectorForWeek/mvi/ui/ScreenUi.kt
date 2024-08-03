package com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForWeek.mvi.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.AppLocale
import com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForWeek.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForWeek.mvi.state.ScreenUiState

@Composable
fun ScreenUi(
    modifier: Modifier = Modifier,
    uiState: ScreenUiState,
    action: (ScreenEvent.Input) -> Unit
) {
    Surface(modifier = modifier) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = AppLocale.current.taskSelectorForWeekDialog.title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            SelectableTasks(
                modifier = Modifier.weight(1f),
                selectableTasks = uiState.selectableTasks
            ) { taskId, isSelected ->
                action(ScreenEvent.Input.TaskSelectionChanged(taskId, isSelected))
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = { action(ScreenEvent.Input.SaveClicked) }
            ) {
                Text(text = AppLocale.current.commonWords.save)
            }
        }
    }
}