package com.javokhir.reachyourgoal.presentation.screen.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForPlan.ThemeSelector
import com.javokhir.reachyourgoal.presentation.screen.settings.mvi.event.ScreenEvent
import com.javokhir.reachyourgoal.presentation.screen.settings.mvi.ui.ScreenUi
import com.javokhir.reachyourgoal.theme.MainAppTheme

class SettingsScreen : Screen {
    @Composable
    override fun Content() {
        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        val viewModel = koinScreenModel<SettingsScreenViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel
                .commands
                .collect {
                    when (it) {
                        ScreenEvent.Command.OpenThemeSettings -> {
                            bottomSheetNavigator.show(ThemeSelector())
                        }

                        ScreenEvent.Command.OpenLanguageSettings -> {
                            // Open language settings
                        }
                    }
                }
        }

        MainAppTheme {
            ScreenUi(uiState = uiState, action = viewModel::action)
        }
    }
}