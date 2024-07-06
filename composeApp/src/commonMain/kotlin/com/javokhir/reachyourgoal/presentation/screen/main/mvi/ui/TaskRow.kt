package com.javokhir.reachyourgoal.presentation.screen.main.mvi.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.javokhir.reachyourgoal.domain.entity.TaskState
import com.javokhir.reachyourgoal.presentation.screen.main.model.TaskAndStates

@Composable
fun TaskRow(
    modifier: Modifier = Modifier,
    taskAndStates: TaskAndStates,
    onStatusChanged: (taskState: TaskState) -> Unit,
) {
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
                .padding(8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(text = taskAndStates.task.name)
        }

        taskAndStates.states.forEach { uiState ->
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
                    .weight(0.4f)
                    .heightIn(min = maxHeight),
                contentAlignment = Alignment.Center
            ) {
                StatusSelector(
                    status = uiState.status,
                    onStatusChanged = {
                        onStatusChanged(
                            uiState.copy(status = it)
                        )
                    }
                )
            }
        }
    }
}