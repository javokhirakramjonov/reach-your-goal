package com.javokhir.reachyourgoal.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weeks")
data class Week(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "from_date")
    val fromDate: String//LocalDate,
) {
    fun getName() = ""//"${fromDate}-${fromDate.plus(6, DateTimeUnit.DAY)}"
}