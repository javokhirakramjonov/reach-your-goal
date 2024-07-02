package com.javokhir.reachyourgoal.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.javokhir.reachyourgoal.domain.entity.Week
import kotlinx.coroutines.flow.Flow

@Dao
interface WeekDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(week: Week)

    @Update
    suspend fun update(week: Week)

    @Delete
    suspend fun delete(week: Week)

    @Query("SELECT * FROM weeks")
    fun getAll(): Flow<List<Week>>

    @Query("SELECT * FROM weeks WHERE id = :id")
    fun getById(id: Int): Flow<Week?>
}