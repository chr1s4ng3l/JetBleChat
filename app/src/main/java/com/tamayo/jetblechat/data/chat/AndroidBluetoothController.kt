package com.tamayo.jetblechat.data.chat

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import com.tamayo.jetblechat.domain.BluetoothDeviceDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@SuppressLint("MissingPermission")
class AndroidBluetoothController(
    private val context: Context
) : BluetoothController {

    private val bluetoothManager by lazy {
        context.getSystemService(BluetoothManager::class.java)
    }

    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }

    private val foundDeviceReceiver = FoundDataReceiver { device ->
        _scannedDevices.update { devices ->
            val newDevices = device.toDomain()
            if (newDevices in devices) devices else devices + newDevices
        }

    }

    init {
        updatePairDevices()
    }

    private val _scannedDevices: MutableStateFlow<List<BluetoothDeviceDomain>> =
        MutableStateFlow(emptyList())
    override val scannedDevices: StateFlow<List<BluetoothDeviceDomain>> get() = _scannedDevices.asStateFlow()

    private val _pairDevices: MutableStateFlow<List<BluetoothDeviceDomain>> =
        MutableStateFlow(emptyList())
    override val pairDevices: StateFlow<List<BluetoothDeviceDomain>> get() = _pairDevices.asStateFlow()

    override fun startScan() {
        if (!hasPermissions(Manifest.permission.BLUETOOTH_SCAN)) {
            return
        }

        context.registerReceiver(
            foundDeviceReceiver,
            IntentFilter(BluetoothDevice.ACTION_FOUND)
        )

        updatePairDevices()

        bluetoothAdapter?.startDiscovery()
    }

    override fun stopScan() {
        if (!hasPermissions(Manifest.permission.BLUETOOTH_SCAN)) {
            return
        }

        bluetoothAdapter?.cancelDiscovery()
    }

    override fun release() {
        context.unregisterReceiver(foundDeviceReceiver)
    }


    private fun updatePairDevices() {
        if (!hasPermissions(Manifest.permission.BLUETOOTH_CONNECT)) {
            return
        }
        bluetoothAdapter?.bondedDevices?.map { it.toDomain() }
            ?.also { devices ->
                _pairDevices.update { devices }
            }
    }

    private fun hasPermissions(permission: String): Boolean {
        return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }
}