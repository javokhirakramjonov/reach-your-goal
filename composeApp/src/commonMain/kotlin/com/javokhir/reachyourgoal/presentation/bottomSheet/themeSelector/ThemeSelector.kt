package com.javokhir.reachyourgoal.presentation.bottomSheet.themeSelector

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.javokhir.reachyourgoal.presentation.bottomSheet.themeSelector.mvi.ui.ScreenUi

class ThemeSelector : Screen {
    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<ThemeSelectorViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        ScreenUi(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
            uiState = uiState,
            action = viewModel::action
        )
    }
}