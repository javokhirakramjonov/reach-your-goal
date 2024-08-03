package com.javokhir.reachyourgoal.locale.components

import kotlinx.datetime.Month

data class MonthNames(
    val getMonthName: (month: Month) -> String
)