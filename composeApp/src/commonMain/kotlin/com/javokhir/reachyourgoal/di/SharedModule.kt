package com.javokhir.reachyourgoal.di

import com.javokhir.reachyourgoal.dao.TaskAndWeekDao
import com.javokhir.reachyourgoal.dao.TaskDao
import com.javokhir.reachyourgoal.dao.TaskStateDao
import com.javokhir.reachyourgoal.dao.WeekDao
import com.javokhir.reachyourgoal.database.ReachYourGoalDatabase
import com.javokhir.reachyourgoal.datastore.AppDatastore
import com.javokhir.reachyourgoal.datastore.SettingsDatastore
import com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForWeek.TaskSelectorForWeekViewModel
import com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForWeek.ThemeSelectorViewModel
import com.javokhir.reachyourgoal.presentation.screen.main.MainScreenViewModel
import com.javokhir.reachyourgoal.presentation.screen.settings.SettingsScreenViewModel
import com.javokhir.reachyourgoal.presentation.screen.statistics.StatisticsScreenViewModel
import com.javokhir.reachyourgoal.presentation.screen.task.TaskScreenViewModel
import com.javokhir.reachyourgoal.presentation.screen.tasks.TasksScreenViewModel
import com.javokhir.reachyourgoal.presentation.screen.week.WeekScreenViewModel
import com.javokhir.reachyourgoal.presentation.screen.weeks.WeeksScreenViewModel
import com.javokhir.reachyourgoal.repository.TaskAndWeekRepository
import com.javokhir.reachyourgoal.repository.TaskRepository
import com.javokhir.reachyourgoal.repository.TaskStateRepository
import com.javokhir.reachyourgoal.repository.WeekRepository
import org.koin.dsl.module

typealias TaskScreenUiState = com.javokhir.reachyourgoal.presentation.screen.task.mvi.state.ScreenUiState
typealias WeekScreenUiState = com.javokhir.reachyourgoal.presentation.screen.week.mvi.state.ScreenUiState
typealias TaskSelectorForWeekUiState = com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForWeek.mvi.state.ScreenUiState

fun appModules() = listOf(
    daoModule,
    viewModelModule,
    dataStoreModule,
    repositoryModule,
    platformModule
)

private val daoModule = module {
    single<TaskDao> {
        val database = get<ReachYourGoalDatabase>()
        database.getTaskDao()
    }

    single<WeekDao> {
        val database = get<ReachYourGoalDatabase>()
        database.getWeekDao()
    }

    single<TaskAndWeekDao> {
        val database = get<ReachYourGoalDatabase>()
        database.getTaskAndWeekDao()
    }

    single<TaskStateDao> {
        val database = get<ReachYourGoalDatabase>()
        database.getTaskStateDao()
    }
}

private val viewModelModule = module {
    factory<MainScreenViewModel> { MainScreenViewModel(get(), get(), get()) }
    factory<TasksScreenViewModel> { TasksScreenViewModel(get()) }
    factory<WeeksScreenViewModel> { WeeksScreenViewModel(get()) }
    factory<TaskScreenViewModel> { (uiState: TaskScreenUiState) ->
        TaskScreenViewModel(
            get(),
            uiState
        )
    }
    factory<WeekScreenViewModel> { (uiState: WeekScreenUiState) ->
        WeekScreenViewModel(
            get(),
            uiState
        )
    }
    factory<TaskSelectorForWeekViewModel> { (uiState: TaskSelectorForWeekUiState) ->
        TaskSelectorForWeekViewModel(
            get(),
            get(),
            get(),
            uiState
        )
    }
    factory<StatisticsScreenViewModel> { StatisticsScreenViewModel(get(), get()) }
    factory<ThemeSelectorViewModel> { ThemeSelectorViewModel(get()) }
    factory<SettingsScreenViewModel> { SettingsScreenViewModel() }
}

private val dataStoreModule = module {
    single<AppDatastore> { AppDatastore(get()) }
    single<SettingsDatastore> { SettingsDatastore(get()) }
}

private val repositoryModule = module {
    single<TaskRepository> { TaskRepository(get()) }
    single<WeekRepository> { WeekRepository(get()) }
    single<TaskAndWeekRepository> { TaskAndWeekRepository(get()) }
    single<TaskStateRepository> { TaskStateRepository(get()) }
}