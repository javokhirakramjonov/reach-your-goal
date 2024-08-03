package com.javokhir.reachyourgoal.locale.languages

import com.javokhir.reachyourgoal.locale.Language
import com.javokhir.reachyourgoal.locale.LocaleStrings
import com.javokhir.reachyourgoal.locale.components.CommonWords
import com.javokhir.reachyourgoal.locale.components.MainScreen
import com.javokhir.reachyourgoal.locale.components.MonthNames
import com.javokhir.reachyourgoal.locale.components.WeekNames
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Month

val UzbekLocale = LocaleStrings(
    name = Language.UZBEK,
    mainScreen = MainScreen(
        currentWeek = "Joriy hafta",
        taskName = "Vazifa nomi",
        noTasks = "Vazifalar mavjud emas",
    ),
    weekNames = WeekNames {
        when (it) {
            DayOfWeek.MONDAY -> "Duşanba"
            DayOfWeek.TUESDAY -> "Seşanba"
            DayOfWeek.WEDNESDAY -> "Çorşanba"
            DayOfWeek.THURSDAY -> "Payşanba"
            DayOfWeek.FRIDAY -> "Juma"
            DayOfWeek.SATURDAY -> "Şanba"
            DayOfWeek.SUNDAY -> "Yakşanba"
            else -> "No such week day"
        }
    },
    monthNames = MonthNames {
        when (it) {
            Month.JANUARY -> "Yanvar"
            Month.FEBRUARY -> "Fevral"
            Month.MARCH -> "Mart"
            Month.APRIL -> "Aprel"
            Month.MAY -> "May"
            Month.JUNE -> "Iyun"
            Month.JULY -> "Iyul"
            Month.AUGUST -> "Avgust"
            Month.SEPTEMBER -> "Sentabr"
            Month.OCTOBER -> "Oktabr"
            Month.NOVEMBER -> "Noyabr"
            Month.DECEMBER -> "Dekabr"
            else -> "No such month"
        }
    },
    commonWords = CommonWords(
        loading = "Yuklanmoqda"
    )
)
