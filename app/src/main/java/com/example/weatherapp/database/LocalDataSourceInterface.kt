package com.example.weatherapp.database

import androidx.lifecycle.LiveData
import com.example.weatherapp.favorite.FavouriteModel
import com.example.weatherapp.networks.Model.WeatherResponse

interface LocalDataSourceInterface {

    fun getWeatherFromDataBase(lat: String, lon: String): WeatherResponse
    suspend fun insertResponseWeather(weatherResponse:WeatherResponse)
    fun deleteWeatherFromFavourite(timeZone: String)
    fun getAllFavourite(): LiveData<List<FavouriteModel>>
    fun addToFavourite()
    suspend fun insertFavWeather(favWeather: FavouriteModel)

//    fun getAllWeatherAlarm(): LiveData<List<AlarmModel>>
//    suspend fun insertWeatherAlarm(weatherAlarm:AlarmModel)
//    suspend fun deleteAlarm(id:Int)
    fun getWeatherFromDataBaseByTimeZone(timeZone: String): WeatherResponse
    fun getAllData(): List<WeatherResponse>
}