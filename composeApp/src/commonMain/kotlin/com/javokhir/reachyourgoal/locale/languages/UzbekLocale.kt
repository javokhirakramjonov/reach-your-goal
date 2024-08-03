package com.javokhir.reachyourgoal.locale.languages

import com.javokhir.reachyourgoal.domain.enums.TaskStatus
import com.javokhir.reachyourgoal.locale.Language
import com.javokhir.reachyourgoal.locale.LocaleStrings
import com.javokhir.reachyourgoal.locale.components.CommonWords
import com.javokhir.reachyourgoal.locale.components.CreateTaskDialog
import com.javokhir.reachyourgoal.locale.components.ErrorMessages
import com.javokhir.reachyourgoal.locale.components.LanguageNames
import com.javokhir.reachyourgoal.locale.components.LanguageSelectorDialog
import com.javokhir.reachyourgoal.locale.components.MainScreen
import com.javokhir.reachyourgoal.locale.components.MonthNames
import com.javokhir.reachyourgoal.locale.components.SettingsScreen
import com.javokhir.reachyourgoal.locale.components.StatisticsScreen
import com.javokhir.reachyourgoal.locale.components.Tabs
import com.javokhir.reachyourgoal.locale.components.TaskSelectorForWeekDialog
import com.javokhir.reachyourgoal.locale.components.TaskStatusNames
import com.javokhir.reachyourgoal.locale.components.ThemeNames
import com.javokhir.reachyourgoal.locale.components.ThemeSelectorDialog
import com.javokhir.reachyourgoal.locale.components.WeekNames
import com.javokhir.reachyourgoal.locale.components.WeekScreen
import com.javokhir.reachyourgoal.theme.ThemeType
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Month

val UzbekLocale = LocaleStrings(
    name = Language.UZBEK,
    mainScreen = MainScreen(
        currentWeek = "Joriy hafta",
        taskName = "Vazifa nomi",
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
        loading = "Yuklanmoqda",
        save = "Saqlash",
        create = "Yaratish"
    ),
    tabs = Tabs(
        main = "Asosiy",
        statistics = "Statistika",
        week = "Hafta",
        settings = "Sozlamalar",
        task = "Vazifa"
    ),
    createTaskDialog = CreateTaskDialog(
        title = "Vazifa yaratish",
        taskName = "Vazifa nomi",
        taskDescription = "Vazifa tavsifi",
    ),
    themeSelectorDialog = ThemeSelectorDialog(
        title = "Mavzu tanlash",
    ),
    taskSelectorForWeekDialog = TaskSelectorForWeekDialog(
        title = "Vazifalar",
    ),
    errorMessages = ErrorMessages(
        noTasksAvailable = "Vazifa mavjud emas",
    ),
    taskStatusNames = TaskStatusNames {
        when (it) {
            TaskStatus.DONE -> "Bajarildi"
            TaskStatus.NOT_MANDATORY -> "Majburiy emas"
            TaskStatus.NOT_STARTED -> "Boshlanmadi"
            TaskStatus.NOT_COMPLETED -> "Bajarilmadi"
        }
    },
    weekScreen = WeekScreen(
        tasks = "Vazifalar",
        updateTasks = "Vazifalarni yangilash",
    ),
    themeNames = ThemeNames {
        when (it) {
            ThemeType.DARK -> "Tungi"
            ThemeType.LIGHT -> "Kunduzgi"
            ThemeType.SYSTEM_DEFAULT -> "Tizim mavzusi"
        }
    },
    settingsScreen = SettingsScreen(
        selectTheme = "Mavzuni tanlash",
        selectLanguage = "Tilni tanlash",
    ),
    statisticsScreen = StatisticsScreen(
        dailyStatistics = "Kunlik statistika",
        weeklyStatistics = "Haftalik statistika",
        allWeeksStatistics = "Barcha haftalar statistikasi",
    ),
    languageNames = LanguageNames {
        when (it) {
            Language.ENGLISH -> "Ingliz tili"
            Language.UZBEK -> "O'zbek tili"
        }
    },
    languageSelectorDialog = LanguageSelectorDialog(
        title = "Tilni tanlash",
    )
)
