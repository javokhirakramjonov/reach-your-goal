package com.javokhir.reachyourgoal.database

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate

class Converters {

    @TypeConverter
    fun toLocalDate(date: String): LocalDate {
        return LocalDate.parse(date)
    }

    @TypeConverter
    fun fromLocalDate(date: LocalDate): String {
        return date.toString()
    }

}