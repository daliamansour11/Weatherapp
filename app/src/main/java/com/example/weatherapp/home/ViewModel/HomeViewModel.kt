/*package com.example.weatherapp.home.ViewModel

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherApp.repo.Repository
import com.example.weatherapp.home.view.SHARD_NAME
import com.example.weatherapp.model.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch*/
/*
class HomeViewModel constructor(private val repo: Repository, var context: Context):ViewModel() {
    lateinit var job: Job
    lateinit var  sharedPreferance : SharedPreferences
    lateinit var  editor:SharedPreferences.Editor
    init {
        sharedPreferance = context.getSharedPreferences(SHARD_NAME, Context.MODE_PRIVATE)

    }
    private  val  _getAllweather = MutableLiveData<WeatherResponse>()
    var getAllweather : LiveData<WeatherResponse> = _getAllweather


    @RequiresApi(Build.VERSION_CODES.M)
    suspend fun getWeatherFromDataBase(lat :String,
                                       lon : String){


        job = viewModelScope.launch(Dispatchers.IO) {
            repo.getWeatherDataFromApiAndInserInDataBase(lat, lon)
        }
        viewModelScope.launch(Dispatchers.IO){
            job.join()
            var timeZone:String?=sharedPreferance.getString("currentzone","")
            var res1= repo.getWeatherFromDataBaseByTimeZone(timeZone!!)

            _getAllweather.postValue(res1)

        }
        repo.remoteDataSource.getCurrentWeather(lat,lon,units = "units",lang = "lang",exclude = "exclude")

    }
}*/