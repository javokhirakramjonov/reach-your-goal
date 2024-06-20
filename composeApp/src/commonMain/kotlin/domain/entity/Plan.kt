package domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock

@Entity(tableName = "plans")
data class Plan(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String? = null,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = Clock.System.now().toEpochMilliseconds(),
)