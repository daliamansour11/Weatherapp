package com.example.weatherapp.Alerts.AlertViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherApp.Alert.AlertModel
import com.example.weatherapp.Repository.RepositoryInterface
import com.example.weatherapp.networks.Model.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlertViewModel (private  val repo : RepositoryInterface) :ViewModel(){



    private var _getWeatherForAlarm = MutableLiveData<WeatherResponse>()

    var dataFormAlarm: LiveData<WeatherResponse> =_getWeatherForAlarm
    private var _getWeatherAlarm = MutableLiveData<List<AlertModel>>()
    var getWeatherAlarm: LiveData<List<AlertModel>> =_getWeatherAlarm

    fun getAllWeatherAlarm(): LiveData<List<AlertModel>> {

        return repo.getAllWeatherAlarms()



    }

    fun deleteALarm(id: Int) {
        viewModelScope.launch (Dispatchers.IO){
            repo.deleteAlarm(id)
        }
    }

    fun getDateFromApiForAlarm(lat: Double, lon: Double,timeZone:String){
        viewModelScope.launch (Dispatchers.IO){
            var result=repo.getDataForAlarm(lat,lon,timeZone)
            _getWeatherForAlarm.postValue(result)
        }
    }
}