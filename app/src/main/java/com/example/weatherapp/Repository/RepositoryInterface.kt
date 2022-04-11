package com.example.weatherapp.Repository

import androidx.lifecycle.LiveData
import com.android.volley.Response
import com.example.weatherApp.Alert.AlertModel
//import com.example.weatherApp.alarm.AlarmModel
import com.example.weatherapp.favorite.FavouriteModel
import com.example.weatherapp.networks.Model.WeatherResponse


interface RepositoryInterface {

    //    fun getDataFromDataBase()
    suspend fun getWeatherDataFromApiAndInserInDataBase(
        lat: String, lon: String
    ):WeatherResponse

    fun getWeatherFromDataBase(
        lat: String,
        lon: String,
        lang: String,
        unit: String
    ): WeatherResponse

    suspend fun insertResponseWeather(weatherResponse: WeatherResponse)
    fun deleteWeatherFromFavourite(timeZone: String)
    fun getFavourite(): LiveData<List<FavouriteModel>>
    fun addToFavourite()
    suspend fun insertFavouriteCountry(favWeather: FavouriteModel)

         fun getAllWeatherAlarms():LiveData<List<AlertModel>>
    suspend fun insertWeatherAlarm(weatherAlarm:AlertModel)
  suspend fun deleteAlarm(id:Int)
    fun getWeatherFromDataBaseByTimeZone(timeZone: String): WeatherResponse
    suspend fun refreshDataBase()


   fun getDataForAlarm(lat:Double, lon:Double, timeZone:String):WeatherResponse
    suspend fun getCurrentWeather(
        lat: String, lon: String,
        units: String, lang: String, exclude: String
    ): retrofit2.Response<WeatherResponse>

}