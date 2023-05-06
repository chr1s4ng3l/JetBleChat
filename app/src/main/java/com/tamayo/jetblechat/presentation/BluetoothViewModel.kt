package com.tamayo.jetblechat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tamayo.jetblechat.data.chat.AndroidBluetoothController
import com.tamayo.jetblechat.data.chat.BluetoothController
import com.tamayo.jetblechat.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class BluetoothViewModel @Inject constructor(
    private val bluetoothController: BluetoothController
) : ViewModel() {

    private val _state = MutableStateFlow(UIState())
    val state = combine(
        bluetoothController.scannedDevices, bluetoothController.pairDevices, _state
    ) { scannedDevices, pairDevices, state ->
        state.copy(scannedDevices = scannedDevices, pairDevices = pairDevices)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _state.value)


    fun startScan() {
        bluetoothController.startScan()
    }

    fun stopScan() {
        bluetoothController.stopScan()
    }
}