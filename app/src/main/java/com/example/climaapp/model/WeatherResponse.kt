package com.example.climaapp.model

data class WeatherResponse(
    val location: Location,
    val current: Current
)

data class Location(
    val name: String,
    val country: String,
    val region: String,
    val localtime: String
)

data class Current(
    val temperature: Int,
    val humidity: Int,
    val weather_descriptions: List<String>,
    val weather_icons: List<String>,
    val feelslike: Int
)
