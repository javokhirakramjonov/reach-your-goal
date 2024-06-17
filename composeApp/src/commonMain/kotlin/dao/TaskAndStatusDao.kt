package dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import domain.entity.TaskAndStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskAndStatusDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(taskAndStatus: TaskAndStatus)

    @Update
    suspend fun update(taskAndStatus: TaskAndStatus)

    @Query("SELECT * FROM task_and_status WHERE id = :id AND task_id = :taskId")
    fun getAllByIdAndTaskId(id: Int, taskId: Int): Flow<TaskAndStatus>

    @Delete
    suspend fun delete(taskAndStatus: TaskAndStatus)

}