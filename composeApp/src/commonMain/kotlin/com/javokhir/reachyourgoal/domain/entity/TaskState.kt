package com.javokhir.reachyourgoal.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.javokhir.reachyourgoal.domain.enums.TaskStatus
import kotlinx.datetime.DayOfWeek

@Entity(
    tableName = "task_states",
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
    ],
    indices = [
        Index(value = ["task_id", "week_id", "day"], unique = true),
    ]
)
data class TaskState(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "task_id")
    val taskId: Int,
    @ColumnInfo(name = "week_id")
    val weekId: Int,
    @ColumnInfo(name = "day")
    val day: DayOfWeek,
    @ColumnInfo(name = "status")
    val status: TaskStatus
)