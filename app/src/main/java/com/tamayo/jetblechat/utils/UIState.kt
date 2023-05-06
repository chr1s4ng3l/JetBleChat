package com.tamayo.jetblechat.utils

import com.tamayo.jetblechat.domain.BluetoothDevice

data class UIState(
    val scannedDevices: List<BluetoothDevice> = emptyList(),
    val pairDevices: List<BluetoothDevice> = emptyList()
)
