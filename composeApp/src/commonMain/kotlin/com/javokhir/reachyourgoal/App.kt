package com.javokhir.reachyourgoal

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.javokhir.reachyourgoal.locale.LocaleStrings
import com.javokhir.reachyourgoal.locale.languages.EnglishLocale
import com.javokhir.reachyourgoal.locale.languages.UzbekLocale
import com.javokhir.reachyourgoal.presentation.screen.dashboard.DashboardScreen
import com.javokhir.reachyourgoal.theme.MainAppTheme
import kotlinx.coroutines.delay

val AppLocale = compositionLocalOf<LocaleStrings> { error("No locale provided") }

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App() {

    var currentLocale by mutableStateOf(UzbekLocale)

    LaunchedEffect(Unit) {
        delay(5000)
        currentLocale = EnglishLocale
    }

    MainAppTheme {
        CompositionLocalProvider(AppLocale provides currentLocale) {
            BottomSheetNavigator(
                sheetBackgroundColor = Color.Transparent
            ) {
                Navigator(DashboardScreen()) {
                    SlideTransition(it)
                }
            }
        }
    }
}