package com.example.weatherapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.weatherapp.favorite.FavouriteModel
import com.example.weatherapp.networks.Model.WeatherResponse

class LocalDataSource constructor( context: Context) : LocalDataSourceInterface{
    override fun getWeatherFromDataBase(lat: String, lon: String): WeatherResponse {
        TODO("Not yet implemented")
    }

    override suspend fun insertResponseWeather(weatherResponse: WeatherResponse) {
        TODO("Not yet implemented")
    }

    override fun deleteWeatherFromFavourite(timeZone: String) {
        TODO("Not yet implemented")
    }

    override fun getAllFavourite(): LiveData<List<FavouriteModel>> {
        TODO("Not yet implemented")
    }

    override fun addToFavourite() {
        TODO("Not yet implemented")
    }

    override suspend fun insertFavWeather(favWeather: FavouriteModel) {
        TODO("Not yet implemented")
    }

    override fun getWeatherFromDataBaseByTimeZone(timeZone: String): WeatherResponse {
        TODO("Not yet implemented")
    }

    override fun getAllData(): List<WeatherResponse> {
        TODO("Not yet implemented")
    }
}
   /* override fun getWeatherFromDataBase(lat: String, lon: String): WeatherResponse {
        // Log.e(TAG, "getWeatherFromDataBase: local source"+currentWeatherDao.getCountryWeather(lat,lon).current )
        return currentWeatherDao.getCountryWeather(lat,lon)    }

    override suspend fun insertResponseWeather(weatherResponse: WeatherResponse) {
        currentWeatherDao.insertCurrentWeather(weatherResponse)
    }

    override fun deleteWeatherFromFavourite(timeZone: String) {
        currentWeatherDao.deleteCurrentWeather(timeZone)
    }

    override fun getAllFavourite(): LiveData<List<FavouriteModel>> {
 return  currentWeatherDao.getAllFavWeather()
    }

    override fun addToFavourite() {
        TODO("Not yet implemented")
    }

    override suspend fun insertFavWeather(favWeather: FavouriteModel) {
        currentWeatherDao.insertFavWeather(favWeather)
    }

    override fun getWeatherFromDataBaseByTimeZone(timeZone: String): WeatherResponse {
return currentWeatherDao.getWeatherFromDataBaseByTimeZone(timeZone)
    }

    override fun getAllData(): List<WeatherResponse> {
return currentWeatherDao.getAllData()   }


}*/



    //////////////////////////
//    override  fun getAllWeatherAlarm(): LiveData<List<AlarmModel>> {
//        return  currentWeatherDao.getAllAlarm()
//    }
//
//    override suspend fun insertWeatherAlarm(weatherAlarm: AlarmModel) {
//        currentWeatherDao.insertWeaherAlarm(weatherAlarm)
//    }
//
//    override suspend fun deleteAlarm(id: Int) {
//        currentWeatherDao.deleteAlarm(id)
