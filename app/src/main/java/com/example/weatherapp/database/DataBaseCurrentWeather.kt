package com.example.weatherapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
//import com.example.weatherApp.alarm.AlarmModel
import com.example.weatherapp.favorite.FavouriteModel
import com.example.weatherapp.networks.Model.WeatherResponse


//    , AlarmModel::class
    @Database(
        entities = arrayOf(WeatherResponse::class, FavouriteModel::class),
        version = 3,
        exportSchema = false
    )
    @TypeConverters(DataConverter::class)
    abstract class CurrentWeatherDataBase : RoomDatabase() {
        abstract fun currentWeatherDao():WeatherDAO



    }