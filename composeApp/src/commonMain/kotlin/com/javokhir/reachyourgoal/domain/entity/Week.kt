package com.javokhir.reachyourgoal.domain.entity

import androidx.compose.runtime.Composable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.javokhir.reachyourgoal.utils.getMonthName
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource

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

        val startDatePart = "${startDate.dayOfMonth}, ${stringResource(getMonthName(startDate))}"
        val endDatePart = "${endDate.dayOfMonth}, ${stringResource(getMonthName(endDate))}"

        return "$startDatePart - $endDatePart"
    }

    suspend fun getName(): String {
        val endDate = getEndDate()

        val startDatePart = "${startDate.dayOfMonth}, ${getString(getMonthName(startDate))}"
        val endDatePart = "${endDate.dayOfMonth}, ${getString(getMonthName(endDate))}"

        return "$startDatePart - $endDatePart"
    }

    private fun getEndDate(): LocalDate {
        return startDate.plus(6, DateTimeUnit.DAY)
    }
}