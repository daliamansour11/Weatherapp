package com.example.weatherApp.alarm.setAlarmDialog

import android.content.ContentValues.TAG
import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Repository.RepositoryInterface

import kotlinx.coroutines.launch
import javax.inject.Inject


class AlarmDialogViewModel  @Inject constructor(var repository: RepositoryInterface) :ViewModel() {

    var fromDate=MutableLiveData<String>("")
    var fromTime=MutableLiveData<String>("")
    var toDate=MutableLiveData<String>("")
    var toTime=MutableLiveData<String>("")
    var isNotification = ObservableBoolean(false)
    var isAlarm= ObservableBoolean(false)
//    private var _getWeatherAlarms = MutableLiveData<List<AlarmModel>>()
//    var getWeatherAlarms: LiveData<List<AlarmModel>> =_getWeatherAlarms
//
//    fun insertAlarmIntoDataBase(weatherAlarm:AlarmModel){
//        Log.e(TAG, "insertAlarmIntoDataBase: ", )
//        viewModelScope.launch {
//                  repository.insertWeatherAlarm(weatherAlarm)

        }
//}}