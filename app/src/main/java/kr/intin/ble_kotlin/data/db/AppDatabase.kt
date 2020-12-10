package kr.intin.ble_kotlin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.intin.ble_kotlin.data.dao.UseTimeDAO
import kr.intin.ble_kotlin.data.entity.UseTime

@Database(entities = [UseTime::class], version = 1)

abstract class AppDatabase: RoomDatabase() {
    abstract fun useTimeDao() : UseTimeDAO
}