package com.javokhir.reachyourgoal.presentation.screen.week.mvi.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.AppLocale
import com.javokhir.reachyourgoal.presentation.screen.week.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.week.mvi.state.ScreenUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenUi(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    uiState: ScreenUiState,
    action: (ScreenEvent.Input) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = uiState.week.getNameComposable()) },
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
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                Snackbar(
                    snackbarData = it,
                    contentColor = MaterialTheme.colorScheme.error,
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = AppLocale.current.weekScreen.tasks,
                style = MaterialTheme.typography.titleMedium
            )

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(uiState.tasks, key = { it.id }) { task ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, MaterialTheme.colorScheme.primary)
                            .padding(8.dp),
                    ) {
                        Text(text = task.name)
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { action(ScreenEvent.Input.UpdateTasksClicked) }
            ) {
                Text(text = AppLocale.current.weekScreen.updateTasks)
            }
        }
    }
}