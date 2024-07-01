package com.javokhir.reachyourgoal.utils

import androidx.compose.runtime.Composable
import com.javokhir.reachyourgoal.theme.ThemeType
import org.jetbrains.compose.resources.stringResource
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.theme_type_dark
import reach_your_goal.composeapp.generated.resources.theme_type_light
import reach_your_goal.composeapp.generated.resources.theme_type_system

@Composable
fun ThemeType.composableName(): String {
    return when (this) {
        ThemeType.SYSTEM_DEFAULT -> stringResource(Res.string.theme_type_system)
        ThemeType.DARK -> stringResource(Res.string.theme_type_dark)
        ThemeType.LIGHT -> stringResource(Res.string.theme_type_light)
    }
}