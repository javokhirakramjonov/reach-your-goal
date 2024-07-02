package com.javokhir.reachyourgoal.database

import androidx.room.TypeConverter
import com.javokhir.reachyourgoal.domain.enums.TaskStatus
import kotlinx.datetime.LocalDate

class Converters {

    @TypeConverter
    fun fromTaskStatusList(statuses: List<TaskStatus>): String {
        return statuses.joinToString(",")
    }

    @TypeConverter
    fun toTaskStatusList(statuses: String): List<TaskStatus> {
        return statuses.split(",").map(TaskStatus::valueOf)
    }

    @TypeConverter
    fun toLocalDate(date: String): LocalDate {
        return LocalDate.parse(date)
    }

    @TypeConverter
    fun fromLocalDate(date: LocalDate): String {
        return date.toString()
    }

}