package com.javokhir.reachyourgoal.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

@Entity(tableName = "weeks")
data class Week(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "start_date")
    val startDate: LocalDate,
) {
    fun getName(): String {
        val endDate = startDate.plus(6, DateTimeUnit.DAY)

        return "${formatDate(startDate)} - ${formatDate(endDate)}"
    }

    private fun formatDate(date: LocalDate): String {
        val day = date.dayOfMonth

        val month = date
            .month
            .name
            .substring(0, 3)
            .lowercase()
            .replaceFirstChar { it.uppercase() }

        return "$day, $month"
    }
}