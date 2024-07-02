package com.javokhir.reachyourgoal.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.javokhir.reachyourgoal.domain.enums.TaskStatus
import com.javokhir.reachyourgoal.utils.weeklyStatusTemplate

@Entity(
    tableName = "task_and_week",
    primaryKeys = ["task_id", "week_id"],
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["task_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = Week::class,
            parentColumns = ["id"],
            childColumns = ["week_id"],
            onDelete = ForeignKey.CASCADE,
        )
    ]
)
data class TaskAndWeek(
    @ColumnInfo(name = "task_id")
    val taskId: Int,
    @ColumnInfo(name = "week_id")
    val weekId: Int,
    @ColumnInfo(name = "statuses")
    val statuses: List<TaskStatus> = weeklyStatusTemplate()
)