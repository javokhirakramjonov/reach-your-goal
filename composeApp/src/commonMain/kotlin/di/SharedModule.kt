package di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dao.PlanDao
import dao.TaskAndPlanDao
import dao.TaskDao
import database.ReachYourGoalDatabase
import datastore.AppDatastore
import datastore.SettingsDatastore
import presentation.bottomSheet.taskSelectorForPlan.TaskSelectorForPlanViewModel
import org.koin.dsl.module
import repository.PlanRepository
import repository.TaskAndPlanRepository
import repository.TaskRepository
import presentation.screen.main.MainScreenViewModel
import presentation.screen.plan.PlanScreenViewModel
import presentation.screen.plans.PlansScreenViewModel
import presentation.screen.task.TaskScreenViewModel
import presentation.screen.tasks.TasksScreenViewModel

typealias TaskScreenUiState = presentation.screen.task.mvi.state.ScreenUiState
typealias PlanScreenUiState = presentation.screen.plan.mvi.state.ScreenUiState
typealias TaskSelectorForPlanUiState = presentation.bottomSheet.taskSelectorForPlan.mvi.state.ScreenUiState

fun sharedModules() = listOf(
    databaseModule,
    daoModule,
    viewModelModule,
    dataStoreModule,
    repositoryModule
)

private val databaseModule = module {
    single<ReachYourGoalDatabase> {
        val builder = get<RoomDatabase.Builder<ReachYourGoalDatabase>>()
        builder
            .fallbackToDestructiveMigration(true)
            .setDriver(BundledSQLiteDriver())
            .build()
    }
}

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
    factory { MainScreenViewModel(get(), get(), get(), get()) }
    factory { TasksScreenViewModel(get()) }
    factory { PlansScreenViewModel(get()) }
    factory { (uiState: TaskScreenUiState) -> TaskScreenViewModel(get(), uiState) }
    factory { (uiState: PlanScreenUiState) -> PlanScreenViewModel(get(), get(), get(), uiState) }
    factory { (uiState: TaskSelectorForPlanUiState) -> TaskSelectorForPlanViewModel(get(), get(), uiState) }
}

private val dataStoreModule = module {
    single<SettingsDatastore> { SettingsDatastore(get()) }
    single<AppDatastore> { AppDatastore(get()) }
}

private val repositoryModule = module {
     single { TaskRepository(get()) }
     single { PlanRepository(get()) }
     single { TaskAndPlanRepository(get()) }
}