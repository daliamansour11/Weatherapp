package com.example.weatherapp.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Repository.RepositoryInterface
import com.example.weatherapp.favorite.FavouriteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MapViewModel  constructor(var repository: RepositoryInterface): ViewModel() {

    fun insertFavPlaceInDataBase(favWeather: FavouriteModel) {
        viewModelScope.launch (Dispatchers.IO){
            repository.insertFavouriteCountry(favWeather)
        }

    }
}