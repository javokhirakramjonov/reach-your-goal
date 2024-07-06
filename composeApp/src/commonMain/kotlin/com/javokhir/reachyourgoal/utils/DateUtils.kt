package com.javokhir.reachyourgoal.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import org.jetbrains.compose.resources.StringResource
import reach_your_goal.composeapp.generated.resources.Res
import reach_your_goal.composeapp.generated.resources.april
import reach_your_goal.composeapp.generated.resources.august
import reach_your_goal.composeapp.generated.resources.december
import reach_your_goal.composeapp.generated.resources.february
import reach_your_goal.composeapp.generated.resources.january
import reach_your_goal.composeapp.generated.resources.july
import reach_your_goal.composeapp.generated.resources.june
import reach_your_goal.composeapp.generated.resources.march
import reach_your_goal.composeapp.generated.resources.may
import reach_your_goal.composeapp.generated.resources.november
import reach_your_goal.composeapp.generated.resources.october
import reach_your_goal.composeapp.generated.resources.september

fun getMonthName(date: LocalDate): StringResource {
    return when (date.month) {
        Month.JANUARY -> Res.string.january
        Month.FEBRUARY -> Res.string.february
        Month.MARCH -> Res.string.march
        Month.APRIL -> Res.string.april
        Month.MAY -> Res.string.may
        Month.JUNE -> Res.string.june
        Month.JULY -> Res.string.july
        Month.AUGUST -> Res.string.august
        Month.SEPTEMBER -> Res.string.september
        Month.OCTOBER -> Res.string.october
        Month.NOVEMBER -> Res.string.november
        Month.DECEMBER -> Res.string.december
        else -> throw IllegalArgumentException("Unknown month")
    }
}