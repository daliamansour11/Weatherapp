package com.example.weatherapp.home.ViewModel

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Repository.RepositoryInterface
import com.example.weatherapp.home.view.SHARD_NAME
import com.example.weatherapp.networks.Model.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeModelView(var iRepo: RepositoryInterface) : ViewModel(){
    lateinit var job: Job
//   lateinit var  sharedPref: SharedPreferences
//    lateinit var editor: SharedPreferences.Editor
//    init { sharedPref =context.getSharedPreferences(SHARD_NAME, Context.MODE_PRIVATE)

//    }
init {
    Log.i(TAG, "instance initializer: Creation of ViewModel")
    getCurrentWeather()
}

    private val _iRepo: RepositoryInterface = iRepo
    private val _WeatherResponse = MutableLiveData<WeatherResponse>()
    init {
        Log.i(TAG, "instance initializer: Creation of ViewModel")
        getCurrentWeather()

    }

//    //Expose returned online Data
    val onlineMovies: LiveData<WeatherResponse> = _WeatherResponse


    fun getCurrentWeather() {
        viewModelScope.launch {
            var respo = iRepo.getWeatherDataFromApiAndInserInDataBase("31.205753", "29.924526")
            System.out.println("respo")
            withContext(Dispatchers.Main) {
                //expose data to main
                Log.i(TAG, "getCurrentWeather: ${respo}")
                _WeatherResponse.postValue(respo)

  Log.i("\n\n viewModel","---------------------------"+respo+"\n\n")

//                job = viewModelScope.launch(Dispatchers.IO) {
//                    iRepo.getWeatherDataFromApiAndInserInDataBase("31,205753", "29.924526")
//                }

//        viewModelScope.launch(Dispatchers.IO) {
//            job.join()
//            var timeZone:String?=sharedPref.getString("currentzone","")
//            var res1= iRepo.getWeatherFromDataBaseByTimeZone(timeZone!!)
//
//            _WeatherResponse.postValue(listOf(res1))
//


            }


        }}
        }

private fun <T> LiveData<T>.postValue(respo: Unit) {

}





