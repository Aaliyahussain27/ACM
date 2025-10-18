package com.example.farmersweatheradvisor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.farmersweatheradvisor.data.RetrofitInstance
import com.example.farmersweatheradvisor.data.WeatherViewModel

class WeatherViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(RetrofitInstance.api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}