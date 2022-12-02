package com.example.weathercomposeneco.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.weathercomposeneco.R
import com.example.weathercomposeneco.presentation.WeatherViewModel

@Composable
fun ErrorDialog(
    viewModel: WeatherViewModel
) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = stringResource(id = R.string.error))
            },
            text = {
                Text(text = viewModel.state.error.toString())
            },
            confirmButton = {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { openDialog.value = false }
                ) {
                    Text(stringResource(id = R.string.ok))
                }
            }
        )
    }
}