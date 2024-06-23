package com.javokhir.reachyourgoal.presentation.screen.settings.mvi.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.javokhir.reachyourgoal.presentation.screen.settings.domain.SettingsItem
import com.javokhir.reachyourgoal.presentation.screen.settings.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.settings.mvi.state.ScreenUiState
import org.jetbrains.compose.resources.stringResource

@Composable
fun ScreenUi(
    uiState: ScreenUiState,
    action: (ScreenEvent.Input) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(uiState.settingsItems) {
                when (it) {
                    is SettingsItem.Clickable -> {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(onClick = it.onClick)
                                .padding(16.dp),
                            text = stringResource(it.titleResource)
                        )
                    }
                }

                HorizontalDivider()
            }
        }
    }
}