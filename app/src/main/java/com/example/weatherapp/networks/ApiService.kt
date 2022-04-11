package com.example.roomdemomvvm.network

import com.example.weatherapp.networks.Model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
const val API_KEY="7ee8aa6b7380a92a05a5afba5c2d96e4"
const val EXCLUDE="minutely"
//api
//http://api.openweathermap.org/data/2.5/onecall?lat=33.44&lon=-94.04&exclude=4,fri&appid=7ee8aa6b7380a92a05a5afba5c2d96e4
interface ApiService {

    @GET("data/2.5/onecall")

    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon:Double,
        @Query("units") unit:String,
        @Query("lang") lang:String,
        @Query("exclude") exclude:String,
        @Query("appid") apiId:String= API_KEY
    ):Response<WeatherResponse>}
