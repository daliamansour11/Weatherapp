package com.example.weatherapp.database

import androidx.room.TypeConverter
import com.example.weatherapp.networks.Model.Daily
import com.example.weatherapp.networks.Model.Hourly
import com.example.weatherapp.networks.Model.Weather


import com.google.gson.Gson

class DataConverter {


    @TypeConverter
    fun  listHourlyToJson (value:List<Hourly>): String = Gson().toJson(value)
    @TypeConverter
    fun  listDailyToJson (value:List<Daily>) = Gson().toJson(value)

    @TypeConverter
    fun  listWeatherToJson (value: List<Weather>) = Gson().toJson(value)
    @TypeConverter
    fun jsonToWeatherList(value: String) = Gson().fromJson(value, Array<Weather>::class.java).toList()
    @TypeConverter
    fun jsonToHourlyList(value: String) = Gson().fromJson(value, Array<Hourly>::class.java).toList()
          @TypeConverter
    fun jsonToDailyList(value: String) = Gson().fromJson(value, Array<Daily>::class.java).toList()


}