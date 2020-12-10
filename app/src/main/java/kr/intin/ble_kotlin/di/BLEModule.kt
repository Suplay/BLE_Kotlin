package kr.intin.ble_kotlin.di

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kr.intin.ble_kotlin.di.annotation.RXService
import kr.intin.ble_kotlin.di.annotation.TXChat
import java.util.*
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object BLEModule {

    @Provides
    @Singleton
    fun provideBLEModule (@ApplicationContext context: Context): BluetoothLeScanner {
        val adapter : BluetoothAdapter by lazy(LazyThreadSafetyMode.NONE) {
            //val manager = context.applicationContext.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
            val manager = context.applicationContext.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
            manager.adapter
        }
        return adapter.bluetoothLeScanner
    }

    @Provides
    @RXService
    fun provideRXServiceUUID () : UUID {
        return UUID.fromString("11223344-5566-7788-99aa-bbccddeeff00")
    }

    @Provides
    @TXChat
    fun provideTXChatUUID () : UUID {
        return UUID.fromString("00004a5b-0000-1000-8000-00805f9b34fb")
    }
}