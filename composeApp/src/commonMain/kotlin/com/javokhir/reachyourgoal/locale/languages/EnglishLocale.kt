package com.javokhir.reachyourgoal.locale.languages

import com.javokhir.reachyourgoal.domain.enums.TaskStatus
import com.javokhir.reachyourgoal.locale.Language
import com.javokhir.reachyourgoal.locale.LocaleStrings
import com.javokhir.reachyourgoal.locale.components.CommonWords
import com.javokhir.reachyourgoal.locale.components.CreateTaskDialog
import com.javokhir.reachyourgoal.locale.components.ErrorMessages
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

val EnglishLocale = LocaleStrings(
    name = Language.ENGLISH,
    mainScreen = MainScreen(
        currentWeek = "Current week",
        taskName = "Task name",
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
        loading = "Loading",
        save = "Save",
        create = "Create"
    ),
    tabs = Tabs(
        main = "Main",
        statistics = "Statistics",
        week = "Week",
        settings = "Settings",
        task = "Task"
    ),
    createTaskDialog = CreateTaskDialog(
        title = "Create Task",
        taskName = "Task name",
        taskDescription = "Task description",
    ),
    themeSelectorDialog = ThemeSelectorDialog(
        title = "Select Theme",
    ),
    taskSelectorForWeekDialog = TaskSelectorForWeekDialog(
        title = "Tasks",
    ),
    errorMessages = ErrorMessages(
        noTasksAvailable = "No tasks available",
    ),
    taskStatusNames = TaskStatusNames {
        when (it) {
            TaskStatus.DONE -> "Done"
            TaskStatus.NOT_MANDATORY -> "Not mandatory"
            TaskStatus.NOT_STARTED -> "Not started"
            TaskStatus.NOT_COMPLETED -> "Not completed"
        }
    },
    weekScreen = WeekScreen(
        tasks = "Tasks",
        updateTasks = "Update tasks",
    ),
    themeNames = ThemeNames {
        when (it) {
            ThemeType.DARK -> "Dark"
            ThemeType.LIGHT -> "Light"
            ThemeType.SYSTEM_DEFAULT -> "System default"
        }
    },
    settingsScreen = SettingsScreen(
        selectTheme = "Select theme",
    ),
    statisticsScreen = StatisticsScreen(
        dailyStatistics = "Daily statistics",
        weeklyStatistics = "Weekly statistics",
        allWeeksStatistics = "All weeks statistics",
    )
)