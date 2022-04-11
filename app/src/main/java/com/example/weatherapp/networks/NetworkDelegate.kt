package com.example.roomdemomvvm.network

import com.example.weatherapp.networks.Model.WeatherResponse


interface NetworkDelegate {
    fun onSuccessResult(weatther: WeatherResponse)
    fun onFailureResult(errorMsg: String)
}