package kr.intin.ble_kotlin.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kr.intin.ble_kotlin.data.entity.UseTime

@Dao
interface UseTimeDAO {

    @Insert
    fun insertTime(useTime: UseTime)

    @Query("SELECT used_time FROM UseTime WHERE used_date == (:used_date)")
    fun getTime(used_date : String): List<Int>

    @Query("SELECT * FROM UseTime")
    fun getAll(): List<UseTime>

    @Delete
    fun delete(useTime: UseTime)

}