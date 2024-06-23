package com.javokhir.reachyourgoal.domain.enums

import androidx.room.Entity

@Entity(tableName = "task_status")
enum class TaskStatus {
    NOT_STARTED,
    NOT_MANDATORY,
    DONE,
    NOT_COMPLETED
}