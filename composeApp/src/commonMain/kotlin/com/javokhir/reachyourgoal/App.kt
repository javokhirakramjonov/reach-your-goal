package com.javokhir.reachyourgoal

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.javokhir.reachyourgoal.presentation.screen.dashboard.DashboardScreen
import com.javokhir.reachyourgoal.theme.MainAppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App() {
    MainAppTheme {
        BottomSheetNavigator(
            sheetBackgroundColor = Color.Transparent
        ) {
            Navigator(DashboardScreen()) {
                SlideTransition(it)
            }
        }
    }
}