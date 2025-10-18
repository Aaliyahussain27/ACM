package com.example.farmersweatheradvisor.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// UI State sealed interface
sealed interface WeatherUiState {
    object Idle : WeatherUiState              // initial state, jab kuch fetch nahi hua
    object Loading : WeatherUiState
    data class Success(
        val temperature: Double,
        val cityName: String,
        val advice: String
    ) : WeatherUiState
    data class Error(
        val message: String
    ) : WeatherUiState
}

class WeatherViewModel(
    private val api: WeatherApi // Already defined elsewhere
) : ViewModel() {

    // initial state = Idle
    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Idle)
    val uiState: StateFlow<WeatherUiState> = _uiState

    // function to fetch weather
    fun fetchWeather(city: String) {
        _uiState.value = WeatherUiState.Loading
        viewModelScope.launch {
            try {
                val response = api.getWeather(city, "OPENWEATHER_API_KEY")
                val tempInCelsius = response.main.temp - 273.15
                val humidity = response.main.humidity               // in %
                val windSpeed = response.wind?.speed                // in m/s
                val rain = response.rain?.`1h` ?: 0.0

                val advice = when {
                    rain > 15 -> "Heavy rainfall expected. Ensure proper drainage to prevent waterlogging in your fields."
                    (windSpeed ?: 0.0) > 12 -> "Strong winds detected. Provide support to crops to prevent damage."
                    humidity < 30 -> "Low humidity conditions. Monitor soil moisture and irrigate crops as needed."
                    tempInCelsius < 10 -> "Temperature is very low. Protect crops from frost and cold conditions."
                    tempInCelsius in 10.0..25.0 -> "Weather is ideal for farming. This is a suitable time for sowing seeds."
                    tempInCelsius in 25.0..35.0 -> "Moderate weather conditions. Continue regular irrigation and fertilizer application."
                    tempInCelsius > 35 -> "High temperatures detected. Protect crops from excessive heat and irrigate in the cooler hours."
                    else -> "Normal weather conditions. Farming activities can continue as usual."
                }

                _uiState.value = WeatherUiState.Success(
                    temperature = tempInCelsius,
                    cityName = response.name,
                    advice=advice
                )
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error(
                    message = e.localizedMessage ?: "Unknown Error"
                )
            }
        }
    }
}