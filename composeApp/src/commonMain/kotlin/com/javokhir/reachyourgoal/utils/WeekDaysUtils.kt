package com.javokhir.reachyourgoal.utils

import androidx.compose.runtime.Composable
import com.javokhir.reachyourgoal.domain.model.WeekDay
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
fun WeekDay.name(): String {
    val resourceName = when (this) {
        WeekDay.MONDAY -> Res.string.monday
        WeekDay.TUESDAY -> Res.string.tuesday
        WeekDay.WEDNESDAY -> Res.string.wednesday
        WeekDay.THURSDAY -> Res.string.thursday
        WeekDay.FRIDAY -> Res.string.friday
        WeekDay.SATURDAY -> Res.string.saturday
        WeekDay.SUNDAY -> Res.string.sunday
    }

    return stringResource(resourceName)
}