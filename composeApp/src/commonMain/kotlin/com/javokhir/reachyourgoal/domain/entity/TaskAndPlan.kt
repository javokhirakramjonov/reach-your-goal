package com.javokhir.reachyourgoal.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.javokhir.reachyourgoal.domain.enums.TaskStatus
import com.javokhir.reachyourgoal.utils.weeklyStatusTemplate

@Entity(
    tableName = "task_and_plan",
    primaryKeys = ["task_id", "plan_id"],
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["task_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = Plan::class,
            parentColumns = ["id"],
            childColumns = ["plan_id"],
            onDelete = ForeignKey.CASCADE,
        )
    ]
)
data class TaskAndPlan(
    @ColumnInfo(name = "task_id")
    val taskId: Int,
    @ColumnInfo(name = "plan_id")
    val planId: Int,
    @ColumnInfo(name = "statuses")
    val statuses: List<TaskStatus> = weeklyStatusTemplate()
)