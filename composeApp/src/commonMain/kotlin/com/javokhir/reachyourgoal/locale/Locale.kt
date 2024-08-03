package com.javokhir.reachyourgoal.locale

import com.javokhir.reachyourgoal.locale.components.CommonWords
import com.javokhir.reachyourgoal.locale.components.CreateTaskDialog
import com.javokhir.reachyourgoal.locale.components.MainScreen
import com.javokhir.reachyourgoal.locale.components.MonthNames
import com.javokhir.reachyourgoal.locale.components.Tabs
import com.javokhir.reachyourgoal.locale.components.TaskSelectorForWeekDialog
import com.javokhir.reachyourgoal.locale.components.ThemeSelectorDialog
import com.javokhir.reachyourgoal.locale.components.WeekNames

enum class Language {
    ENGLISH,
    UZBEK
}

data class LocaleStrings(
    val name: Language,
    val mainScreen: MainScreen,
    val taskSelectorForWeekDialog: TaskSelectorForWeekDialog,
    val themeSelectorDialog: ThemeSelectorDialog,
    val createTaskDialog: CreateTaskDialog,
    val tabs: Tabs,
    val monthNames: MonthNames,
    val weekNames: WeekNames,
    val commonWords: CommonWords
)