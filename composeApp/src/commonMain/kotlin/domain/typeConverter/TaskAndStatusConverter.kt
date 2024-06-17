package domain.typeConverter

import androidx.room.TypeConverter
import domain.enums.TaskStatus

class TaskAndStatusConverter {

    @TypeConverter
    fun fromStatuses(statuses: List<TaskStatus>): String {
        return statuses
            .map(TaskStatus::name)
            .joinToString(",")
    }

    @TypeConverter
    fun toStatuses(data: String): List<TaskStatus> {
        return data
            .split(",")
            .map(TaskStatus::valueOf)
    }
}