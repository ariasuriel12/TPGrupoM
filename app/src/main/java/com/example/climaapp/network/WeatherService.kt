package com.example.climaapp.network

import com.example.climaapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("current")
    fun getWeather(
        @Query("access_key") apiKey: String,
        @Query("query") city: String
    ): Call<WeatherResponse>
}