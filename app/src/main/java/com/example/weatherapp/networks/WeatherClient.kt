package com.example.roomdemomvvm.network

import com.example.weatherapp.networks.Model.WeatherResponse
import retrofit2.Response


private const val TAG = "AllWeatherFeature"

class WeatherClient() : RemoteSource {
    override suspend fun getCurrentWeatherOverNetwork(): Response<WeatherResponse> {
        val movieService = RetrofitHelper.getInstance().create(ApiService::class.java)
        val response = movieService.getCurrentWeather(31.29,29.89,"metric","zh_cn","minutely" )
        return response    }


   companion object{
        private var instance: WeatherClient? = null
        fun getInstance(): WeatherClient{
            return  instance?: WeatherClient()
        }
}


    }

