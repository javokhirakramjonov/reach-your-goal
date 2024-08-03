package com.javokhir.reachyourgoal.locale.languages

import com.javokhir.reachyourgoal.locale.Language
import com.javokhir.reachyourgoal.locale.LocaleStrings
import com.javokhir.reachyourgoal.locale.components.CommonWords
import com.javokhir.reachyourgoal.locale.components.MainScreen
import com.javokhir.reachyourgoal.locale.components.MonthNames
import com.javokhir.reachyourgoal.locale.components.WeekNames
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Month

val EnglishLocale = LocaleStrings(
    name = Language.ENGLISH,
    mainScreen = MainScreen(
        currentWeek = "Current week",
        taskName = "Task name",
        noTasks = "No tasks"
    ),
    weekNames = WeekNames {
        when (it) {
            DayOfWeek.MONDAY -> "Monday"
            DayOfWeek.TUESDAY -> "Tuesday"
            DayOfWeek.WEDNESDAY -> "Wednesday"
            DayOfWeek.THURSDAY -> "Thursday"
            DayOfWeek.FRIDAY -> "Friday"
            DayOfWeek.SATURDAY -> "Saturday"
            DayOfWeek.SUNDAY -> "Sunday"
            else -> "No such week day"
        }
    },
    monthNames = MonthNames {
        when (it) {
            Month.JANUARY -> "January"
            Month.FEBRUARY -> "February"
            Month.MARCH -> "March"
            Month.APRIL -> "April"
            Month.MAY -> "May"
            Month.JUNE -> "June"
            Month.JULY -> "July"
            Month.AUGUST -> "August"
            Month.SEPTEMBER -> "September"
            Month.OCTOBER -> "October"
            Month.NOVEMBER -> "November"
            Month.DECEMBER -> "December"
            else -> "No such month"
        }
    },
    commonWords = CommonWords(
        loading = "Loading"
    )
)