package com.example.farmersweatheradvisor.data

import retrofit2.http.GET
import retrofit2.http.Query
import kotlinx.serialization.Serializable

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): WeatherResponse
}

@Serializable
data class WeatherResponse(
    val name: String,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind? = null,        // nullable
    val rain: Rain? = null
)

@Serializable
data class Main(
    val temp: Double,
    val humidity: Int
)

@Serializable
data class Wind(
    val speed: Double
)

@Serializable
data class Rain(
    val `1h`: Double? = 0.0        // use backticks for 1h
)

@Serializable
data class Weather(
    val main: String,
    val description: String
)
