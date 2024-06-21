package com.javokhir.reachyourgoal.presentation.screen.task.mvi.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.presentation.screen.task.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.task.mvi.state.ScreenUiState
import org.jetbrains.compose.resources.stringResource
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.task_screen_task_description
import reach_your_goal.composeapp.generated.resources.task_screen_task_name
import reach_your_goal.composeapp.generated.resources.task_screen_update_task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenUi(
    modifier: Modifier = Modifier,
    uiState: ScreenUiState,
    action: (ScreenEvent.Input) -> Unit
) {
    var name by remember { mutableStateOf(uiState.task.name) }
    var description by remember { mutableStateOf(uiState.task.description.orEmpty()) }

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = uiState.task.name) },
                navigationIcon = {
                    IconButton(
                        onClick = { action(ScreenEvent.Input.Exit) }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = { name = it },
                label = { Text(text = stringResource(Res.string.task_screen_task_name)) },
                maxLines = 2,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        action(ScreenEvent.Input.TaskChanged(name, description.ifEmpty { null }))
                    }
                )
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = description,
                onValueChange = { description = it },
                label = { Text(text = stringResource(Res.string.task_screen_task_description)) },
                maxLines = 4,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        action(ScreenEvent.Input.TaskChanged(name, description.ifEmpty { null }))
                    }
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    action(ScreenEvent.Input.TaskChanged(name, description.ifEmpty { null }))
                }
            ) {
                Text(text = stringResource(Res.string.task_screen_update_task))
            }

            IconButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { action(ScreenEvent.Input.DeleteTask) }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}