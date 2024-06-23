package com.javokhir.reachyourgoal.presentation.bottomSheet.themeSelector.mvi.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.presentation.bottomSheet.themeSelector.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.bottomSheet.themeSelector.mvi.state.ScreenUiState
import org.jetbrains.compose.resources.stringResource
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.theme_types

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
                text = stringResource(Res.string.theme_types),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn {
                items(uiState.themeTypes) {
                    ThemeTypeItem(
                        themeType = it,
                        isSelected = it == uiState.currentTheme
                    ) {
                        action(ScreenEvent.Input.ThemeTypeSelected(it))
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}