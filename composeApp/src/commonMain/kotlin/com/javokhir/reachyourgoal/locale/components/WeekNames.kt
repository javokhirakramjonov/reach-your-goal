package com.javokhir.reachyourgoal.locale.components

import kotlinx.datetime.DayOfWeek

class WeekNames(
    val getName: (dayOfWeek: DayOfWeek) -> String
)