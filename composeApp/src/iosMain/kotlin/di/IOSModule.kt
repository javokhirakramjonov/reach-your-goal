package di

import database.getTasksDatabase
import org.koin.dsl.module

fun iosModules() = listOf(
    databaseModule
)

private val databaseModule = module {
    single {
        getTasksDatabase()
    }
}