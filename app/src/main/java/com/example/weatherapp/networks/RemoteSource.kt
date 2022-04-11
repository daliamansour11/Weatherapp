package com.example.roomdemomvvm.network

import com.example.weatherapp.networks.Model.WeatherResponse
import retrofit2.Response

interface RemoteSource {
    suspend fun getCurrentWeatherOverNetwork(): Response<WeatherResponse>
}