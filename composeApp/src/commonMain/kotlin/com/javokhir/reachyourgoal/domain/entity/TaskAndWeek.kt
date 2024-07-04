package com.javokhir.reachyourgoal.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

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
    ],
)
data class TaskAndWeek(
    @ColumnInfo(name = "task_id", index = true)
    val taskId: Int,
    @ColumnInfo(name = "week_id", index = true)
    val weekId: Int,
)