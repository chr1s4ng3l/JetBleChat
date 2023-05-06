package com.tamayo.jetblechat.data.chat

import com.tamayo.jetblechat.domain.BluetoothDeviceDomain
import kotlinx.coroutines.flow.StateFlow

interface BluetoothController {

    val scannedDevices: StateFlow<List<BluetoothDeviceDomain>>
    val pairDevices: StateFlow<List<BluetoothDeviceDomain>>

    fun startScan()
    fun stopScan()

    fun release()
}