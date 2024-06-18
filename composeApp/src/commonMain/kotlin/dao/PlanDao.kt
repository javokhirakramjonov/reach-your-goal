package dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import domain.entity.Plan
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plan: Plan) : Long

    @Update
    suspend fun update(plan: Plan)

    @Delete
    suspend fun delete(plan: Plan)

    @Query("SELECT * FROM plans")
    fun getAll(): Flow<List<Plan>>

    @Query("SELECT * FROM plans WHERE id = :id")
    fun getById(id: Int): Flow<Plan?>
}