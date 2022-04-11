package com.example.weatherapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
//import com.example.weatherApp.alarm.AlertModel
import com.example.weatherapp.favorite.FavouriteModel
import com.example.weatherapp.networks.Model.WeatherResponse

@Dao
interface WeatherDAO {

    @Query("SELECT * FROM FavouriteWeather")
    fun getAllFavWeather():LiveData<List<FavouriteModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(currentWeatherEntity: WeatherResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavWeather(favWeather: FavouriteModel)
    @Query("SELECT * FROM currentweather")
    fun getAllData(): List<WeatherResponse>
    @Query("SELECT * FROM CurrentWeather WHERE lat=:lat AND lon=:lon ")
    fun getCountryWeather(lat: String, lon: String): WeatherResponse
    @Query("SELECT * FROM CurrentWeather WHERE timezone= :timeZone")

    fun getWeatherFromDataBaseByTimeZone(timeZone: String): WeatherResponse

    @Query("DELETE FROM FavouriteWeather WHERE timezone = :timezone")
    fun deleteCurrentWeather(timezone:String)

    @Query("DELETE FROM CurrentWeather WHERE timezone = :timezone")
    fun updateAndAddedToFavorite(timezone:String)

    @Query("Select count(*) from CurrentWeather")
    fun getCount():Int
//    ////////alarm
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertWeaherAlarm(alarm:AlertModel)
//
//    @Query("SELECT * FROM alarmTable")
//    fun getAllAlarm():LiveData<List<AlertModel>>
//
//    @Query("DELETE FROM alarmTable WHERE alarmId = :id")
//    suspend fun deleteAlarm(id : Int)

}