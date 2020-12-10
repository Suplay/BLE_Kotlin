package kr.intin.ble_kotlin.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kr.intin.ble_kotlin.data.dao.UseTimeDAO
import kr.intin.ble_kotlin.data.db.AppDatabase


@Module
@InstallIn(ApplicationComponent::class)
object DBModule {

    @Provides
    fun provideDB(@ApplicationContext context: Context): UseTimeDAO {
        return Room.databaseBuilder(context,
        AppDatabase::class.java, "usedTime").build().useTimeDao()
    }
}