package com.javokhir.reachyourgoal

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.javokhir.reachyourgoal.datastore.SettingsDatastore
import com.javokhir.reachyourgoal.locale.LocaleStrings
import com.javokhir.reachyourgoal.locale.languages.EnglishLocale
import com.javokhir.reachyourgoal.presentation.screen.dashboard.DashboardScreen
import com.javokhir.reachyourgoal.theme.MainAppTheme
import org.koin.compose.koinInject

val AppLocale = compositionLocalOf<LocaleStrings> { error("No locale provided") }

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun App() {

    val settingsDatastore = koinInject<SettingsDatastore>()
    val currentLocale by settingsDatastore.getAppLocale().collectAsState(initial = EnglishLocale)

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