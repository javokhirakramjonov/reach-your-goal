package com.javokhir.reachyourgoal.di

import com.javokhir.reachyourgoal.dao.PlanDao
import com.javokhir.reachyourgoal.dao.TaskAndPlanDao
import com.javokhir.reachyourgoal.dao.TaskDao
import com.javokhir.reachyourgoal.database.ReachYourGoalDatabase
import com.javokhir.reachyourgoal.datastore.AppDatastore
import com.javokhir.reachyourgoal.datastore.SettingsDatastore
import com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForPlan.TaskSelectorForPlanViewModel
import com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForPlan.ThemeSelectorViewModel
import com.javokhir.reachyourgoal.presentation.screen.main.MainScreenViewModel
import com.javokhir.reachyourgoal.presentation.screen.plan.PlanScreenViewModel
import com.javokhir.reachyourgoal.presentation.screen.plans.PlansScreenViewModel
import com.javokhir.reachyourgoal.presentation.screen.settings.SettingsScreenViewModel
import com.javokhir.reachyourgoal.presentation.screen.task.TaskScreenViewModel
import com.javokhir.reachyourgoal.presentation.screen.tasks.TasksScreenViewModel
import com.javokhir.reachyourgoal.repository.PlanRepository
import com.javokhir.reachyourgoal.repository.TaskAndPlanRepository
import com.javokhir.reachyourgoal.repository.TaskRepository
import org.koin.dsl.module

typealias TaskScreenUiState = com.javokhir.reachyourgoal.presentation.screen.task.mvi.state.ScreenUiState
typealias PlanScreenUiState = com.javokhir.reachyourgoal.presentation.screen.plan.mvi.state.ScreenUiState
typealias TaskSelectorForPlanUiState = com.javokhir.reachyourgoal.presentation.bottomSheet.taskSelectorForPlan.mvi.state.ScreenUiState

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

    single<PlanDao> {
        val database = get<ReachYourGoalDatabase>()
        database.getPlanDao()
    }

    single<TaskAndPlanDao> {
        val database = get<ReachYourGoalDatabase>()
        database.getTaskAndPlanDao()
    }
}

private val viewModelModule = module {
    factory<MainScreenViewModel> { MainScreenViewModel(get(), get(), get(), get()) }
    factory<TasksScreenViewModel> { TasksScreenViewModel(get()) }
    factory<PlansScreenViewModel> { PlansScreenViewModel(get()) }
    factory<TaskScreenViewModel> { (uiState: TaskScreenUiState) ->
        TaskScreenViewModel(
            get(),
            uiState
        )
    }
    factory<PlanScreenViewModel> { (uiState: PlanScreenUiState) ->
        PlanScreenViewModel(
            get(),
            get(),
            get(),
            uiState
        )
    }
    factory<TaskSelectorForPlanViewModel> { (uiState: TaskSelectorForPlanUiState) ->
        TaskSelectorForPlanViewModel(
            get(),
            get(),
            uiState
        )
    }
    factory<ThemeSelectorViewModel> { ThemeSelectorViewModel(get()) }
    factory<SettingsScreenViewModel> { SettingsScreenViewModel() }
}

private val dataStoreModule = module {
    single<SettingsDatastore> { SettingsDatastore(get()) }
    single<AppDatastore> { AppDatastore(get()) }
}

private val repositoryModule = module {
    single<TaskRepository> { TaskRepository(get()) }
    single<PlanRepository> { PlanRepository(get()) }
    single<TaskAndPlanRepository> { TaskAndPlanRepository(get()) }
}