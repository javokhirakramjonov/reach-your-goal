package com.javokhir.reachyourgoal.domain.entity

import androidx.compose.runtime.Composable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.javokhir.reachyourgoal.AppLocale
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
    @Composable
    fun getNameComposable(): String {
        val endDate = getEndDate()

        val startDatePart =
            "${startDate.dayOfMonth}, ${AppLocale.current.monthNames.getMonthName(startDate.month)}"
        val endDatePart =
            "${endDate.dayOfMonth}, ${AppLocale.current.monthNames.getMonthName(endDate.month)}"

        return "$startDatePart - $endDatePart"
    }

    private fun getEndDate(): LocalDate {
        return startDate.plus(6, DateTimeUnit.DAY)
    }
}