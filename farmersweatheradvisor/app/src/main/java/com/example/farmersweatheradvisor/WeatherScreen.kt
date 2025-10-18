package com.example.farmersweatheradvisor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.farmersweatheradvisor.data.WeatherViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.farmersweatheradvisor.data.WeatherUiState


@Composable
fun WeatherScreen(modifier: Modifier = Modifier){
    var city by remember { mutableStateOf("") }
    val weatherViewModel: WeatherViewModel = viewModel()
    val uiState by weatherViewModel.uiState.collectAsStateWithLifecycle()

    Column(verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)){
        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            placeholder = { Text("Enter a city name") },
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
        )
        Button(onClick={weatherViewModel.fetchWeather(city)},
            modifier = Modifier.padding(10.dp)) {
            Text("Search")
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when(uiState) {
                is WeatherUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is WeatherUiState.Success -> {
                    val data = uiState as WeatherUiState.Success
                    Text("City: ${data.cityName}")
                    Text("Temperature: ${"%.2f".format(data.temperature)}°C")
                }
                is WeatherUiState.Error -> {
                    val error = uiState as WeatherUiState.Error
                    Text("Error: ${error.message}", color = Color.Red)
                }

                else -> {}
            }
        }
    }
}