package domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import domain.enums.TaskStatus
import domain.typeConverter.TaskAndStatusConverter

@Entity(
    tableName = "task_and_status",
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = ["id"],
            childColumns = ["task_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
@TypeConverters(TaskAndStatusConverter::class)
data class TaskAndStatus(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "task_id")
    val taskId: Int,
    @ColumnInfo(name = "statuses")
    val statuses: List<TaskStatus>
)