package com.example.weatherApp.favorite

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Repository.RepositoryInterface
import com.example.weatherapp.favorite.FavouriteModel
import com.example.weatherapp.networks.Model.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class FavouriteViewModel constructor(
   var  repository: RepositoryInterface
)
    :ViewModel() {
    fun getAllFavouriteFromDataBase():LiveData<List<FavouriteModel>>{

             return repository.getFavourite()
        }


    init {
        Log.i(TAG, "instance initializer: Creation of ViewModel")
    }

    private val _iRepo: RepositoryInterface = repository
    private val _WeatherResponse = MutableLiveData<WeatherResponse>()
    init {
        Log.i(TAG, "instance initializer: Creation of ViewModel")

    }

    //    //Expose returned online Data
    val favouritWeather: LiveData<WeatherResponse> = _WeatherResponse

    fun deleteFromFav(timezone:String){
         Log.e(TAG, "deleteFromFav: ", )
         viewModelScope.launch(Dispatchers.IO) {
             repository.deleteWeatherFromFavourite(timezone)
             repository.getCurrentWeather(lang = "lang",lat = "lat",lon = "lon",exclude = "exclude",units = "units")


         }
     }



}