package com.tamayo.jetblechat.data.chat

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.tamayo.jetblechat.domain.BluetoothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toDomain(): BluetoothDeviceDomain =
    BluetoothDeviceDomain(
        name = name,
        address = address

    )