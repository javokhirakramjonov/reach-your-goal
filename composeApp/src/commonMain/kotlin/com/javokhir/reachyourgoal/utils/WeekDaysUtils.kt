package com.javokhir.reachyourgoal.utils

import androidx.compose.runtime.Composable
import com.javokhir.reachyourgoal.domain.model.WeekDays
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
fun WeekDays.name(): String {
    val resourceName = when (this) {
        WeekDays.MONDAY -> Res.string.monday
        WeekDays.TUESDAY -> Res.string.tuesday
        WeekDays.WEDNESDAY -> Res.string.wednesday
        WeekDays.THURSDAY -> Res.string.thursday
        WeekDays.FRIDAY -> Res.string.friday
        WeekDays.SATURDAY -> Res.string.saturday
        WeekDays.SUNDAY -> Res.string.sunday
    }

    return stringResource(resourceName)
}