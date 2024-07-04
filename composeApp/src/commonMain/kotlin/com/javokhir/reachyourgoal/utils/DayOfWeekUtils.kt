package com.javokhir.reachyourgoal.utils

import androidx.compose.runtime.Composable
import kotlinx.datetime.DayOfWeek
import org.jetbrains.compose.resources.stringResource
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.friday
import reach_your_goal.composeapp.generated.resources.monday
import reach_your_goal.composeapp.generated.resources.saturday
import reach_your_goal.composeapp.generated.resources.sunday
import reach_your_goal.composeapp.generated.resources.thursday
import reach_your_goal.composeapp.generated.resources.tuesday
import reach_your_goal.composeapp.generated.resources.wednesday

@Composable
fun DayOfWeek.name(): String {
    val resourceName = when (this) {
        DayOfWeek.MONDAY -> Res.string.monday
        DayOfWeek.TUESDAY -> Res.string.tuesday
        DayOfWeek.WEDNESDAY -> Res.string.wednesday
        DayOfWeek.THURSDAY -> Res.string.thursday
        DayOfWeek.FRIDAY -> Res.string.friday
        DayOfWeek.SATURDAY -> Res.string.saturday
        DayOfWeek.SUNDAY -> Res.string.sunday
        else -> throw IllegalArgumentException("Unknown day of week: $this")
    }

    return stringResource(resourceName)
}