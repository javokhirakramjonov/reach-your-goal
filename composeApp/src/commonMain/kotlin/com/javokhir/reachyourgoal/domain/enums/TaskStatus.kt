package com.javokhir.reachyourgoal.domain.enums

import androidx.room.Entity

@Entity(tableName = "task_status")
enum class TaskStatus {
    DONE,
    NOT_MANDATORY,
    NOT_STARTED,
    NOT_COMPLETED
}