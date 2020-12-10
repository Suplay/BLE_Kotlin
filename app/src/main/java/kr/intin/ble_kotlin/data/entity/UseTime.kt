package kr.intin.ble_kotlin.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity
data class UseTime(
    @PrimaryKey(autoGenerate = true) val index: Int,
    @ColumnInfo(name = "used_time") val usedTime: Int?,
    @ColumnInfo(name = "used_date") val usedDate: String?, //yyyy-MM-dd
    @ColumnInfo(name = "started_time") val startedTime: String? // yyyy-MM-dd HH:mm:ss
)
