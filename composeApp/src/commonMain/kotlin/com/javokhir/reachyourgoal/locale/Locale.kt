package com.javokhir.reachyourgoal.locale

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

enum class Language {
    ENGLISH,
    UZBEK
}

data class LocaleStrings(
    val name: Language,
    val mainScreen: MainScreen,
    val taskSelectorForWeekDialog: TaskSelectorForWeekDialog,
    val themeSelectorDialog: ThemeSelectorDialog,
    val languageSelectorDialog: LanguageSelectorDialog,
    val createTaskDialog: CreateTaskDialog,
    val tabs: Tabs,
    val themeNames: ThemeNames,
    val monthNames: MonthNames,
    val weekNames: WeekNames,
    val commonWords: CommonWords,
    val taskStatusNames: TaskStatusNames,
    val weekScreen: WeekScreen,
    val errorMessages: ErrorMessages,
    val statisticsScreen: StatisticsScreen,
    val settingsScreen: SettingsScreen,
    val languageNames: LanguageNames
)