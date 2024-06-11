package di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dao.TaskDao
import database.TaskDatabase
import org.koin.dsl.module
import screen.home.HomeScreenViewModel

fun sharedModules() = listOf(
    databaseModule,
    daoModule,
    viewModelModule
)

private val databaseModule = module {
    single<TaskDatabase> {
        val builder = get<RoomDatabase.Builder<TaskDatabase>>()
        builder
            .fallbackToDestructiveMigration(true)
            .setDriver(BundledSQLiteDriver())
            .build()
    }
}

private val daoModule = module {
    single<TaskDao> {
        val database = get<TaskDatabase>()
        database.getTaskDao()
    }
}

private val viewModelModule = module {
    factory { HomeScreenViewModel(get()) }
}