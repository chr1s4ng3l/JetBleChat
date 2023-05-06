package com.tamayo.jetblechat.presentation.uicomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tamayo.jetblechat.domain.BluetoothDevice
import com.tamayo.jetblechat.ui.theme.Pink80
import com.tamayo.jetblechat.ui.theme.Purple80
import com.tamayo.jetblechat.ui.theme.PurpleGrey40
import com.tamayo.jetblechat.ui.theme.PurpleGrey80
import com.tamayo.jetblechat.utils.UIState

@Composable
fun HomeScreen(
    state: UIState,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit
) {


    Column(modifier = Modifier.fillMaxSize()) {

        BluetoothDeviceList(
            pairDevices = state.pairDevices,
            scannedDevices = state.scannedDevices,
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {

            Button(
                onClick = { onStartScan() },
            ) {
                Text(text = "Start scan", color = Color.White, fontWeight = FontWeight.ExtraBold)
            }

            Button(
                onClick = { onStopScan() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            ) {
                Text(text = "Stop scan", color = Color.White, fontWeight = FontWeight.ExtraBold)
            }

        }


    }
}

@Composable
fun BluetoothDeviceList(
    pairDevices: List<BluetoothDevice>,
    scannedDevices: List<BluetoothDevice>,
    onClick: (BluetoothDevice) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier,
    ) {
        item {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Pair Devices",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        itemsIndexed(pairDevices) { _, device ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { onClick(device) },
                text = device.name ?: "No name"
            )
        }

        item {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Scan Devices",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        itemsIndexed(scannedDevices) { _, device ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { onClick(device) },
                text = device.name ?: "No name"
            )
        }

    }


}


