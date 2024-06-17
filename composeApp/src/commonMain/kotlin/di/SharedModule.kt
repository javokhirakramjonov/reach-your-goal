package di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dao.PlanDao
import dao.TaskAndPlanDao
import dao.TaskDao
import database.ReachYourGoalDatabase
import datastore.AppDatastore
import datastore.SettingsDatastore
import dialog.bottomSheet.taskSelectorForPlan.TaskSelectorForPlanViewModel
import org.koin.dsl.module
import screen.main.MainScreenViewModel
import screen.plan.PlanScreenViewModel
import screen.plans.PlansScreenViewModel
import screen.task.TaskScreenViewModel
import screen.tasks.TasksScreenViewModel

typealias TaskScreenUiState = screen.task.mvi.state.ScreenUiState
typealias PlanScreenUiState = screen.plan.mvi.state.ScreenUiState
typealias TaskSelectorForPlanUiState = dialog.bottomSheet.taskSelectorForPlan.mvi.state.ScreenUiState

fun sharedModules() = listOf(
    databaseModule,
    daoModule,
    viewModelModule,
    dataStoreModule
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
    factory { MainScreenViewModel(get(), get()) }
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