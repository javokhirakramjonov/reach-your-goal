package com.javokhir.reachyourgoal.presentation.screen.weeks.mvi.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.javokhir.reachyourgoal.presentation.screen.weeks.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.weeks.mvi.state.ScreenUiState

@Composable
fun ScreenUi(
    uiState: ScreenUiState,
    action: (ScreenEvent.Input) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    action(ScreenEvent.Input.CreateNextWeek)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
        ) {
            WeekList(
                modifier = Modifier.weight(1f),
                columns = 1,
                weeks = uiState.weeks,
                onWeekClick = {
                    action(ScreenEvent.Input.OpenWeek(it))
                })
        }
    }
}