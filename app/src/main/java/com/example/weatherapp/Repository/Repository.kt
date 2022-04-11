package com.example.weatherapp.Repository

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.example.roomdemomvvm.network.RemoteSource
import com.example.weatherApp.Alert.AlertModel
import com.example.weatherapp.Utils
import com.example.weatherapp.database.LocalDataSource
import com.example.weatherapp.database.LocalDataSourceInterface
import com.example.weatherapp.favorite.FavouriteModel
import com.example.weatherapp.networks.Model.WeatherResponse

import kotlinx.coroutines.Job
import retrofit2.Response
 class Repository constructor(    val localSource: LocalDataSourceInterface,
                                 val remoteSource: RemoteSource,
                                 var context: Context
) : RepositoryInterface {

    companion object {
        private var instance: Repository? = null
        fun getInstance(
            remoteSource: RemoteSource,
            localSource: LocalDataSource,
            context: Context
        ): Repository {
            return instance ?: Repository(
                localSource, remoteSource, context
            )
        }
    }
    lateinit var sharedPref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var lang: String
    lateinit var units: String
    lateinit var re: Response<WeatherResponse>
    private var jop: Job? = null
    private fun init() {
        sharedPref = context.getSharedPreferences("SHARD_NAME", Context.MODE_PRIVATE)
        editor = sharedPref.edit()
        lang = sharedPref.getString(Utils.LANG_UNIT, "en").toString()
        units = sharedPref.getString(Utils.WIND_UNIT, "metric").toString()
    }

    override suspend fun getWeatherDataFromApiAndInserInDataBase(lat: String, lon: String):WeatherResponse {
        System.out.println("we are in repo")


        init()
        var response =
            remoteSource.getCurrentWeatherOverNetwork()
        if (response.isSuccessful) {
            System.out.println("We are here")
            Log.e("TAG", "response" + response.body())
            editor.putString("currentzone", response.body().toString())
            editor.commit()
            response.body()?.let {


                //localSource.insertResponseWeather(WeatherResponse("",true,1,35.8,28.97,))

            }
            return response.body()!!
        } else {
            System.out.println("We are here")

            Log.e("TAG", "Error" + response.errorBody())
            return WeatherResponse()
        }
    }
    override fun getWeatherFromDataBase(
        lat: String,
        lon: String,
        lang: String,
        unit: String
    ): WeatherResponse {

        return localSource.getWeatherFromDataBase(lat, lon)
    }


    override suspend fun insertResponseWeather(weatherResponse: WeatherResponse) {
        localSource.insertResponseWeather(weatherResponse)
    }

    override fun deleteWeatherFromFavourite(timeZone: String) {
        localSource.deleteWeatherFromFavourite(timeZone)


    }

    override fun getFavourite(): LiveData<List<FavouriteModel>> {
        return localSource.getAllFavourite()

    }

    override fun addToFavourite() {
        localSource.addToFavourite()
    }

    override suspend fun insertFavouriteCountry(favWeather: FavouriteModel) {
        localSource.insertFavWeather(favWeather)

    }

     override fun getAllWeatherAlarms(): LiveData<List<AlertModel>> {
         TODO("Not yet implemented")
     }

     override suspend fun insertWeatherAlarm(weatherAlarm: AlertModel) {
         TODO("Not yet implemented")
     }

     override suspend fun deleteAlarm(id: Int) {
         TODO("Not yet implemented")
     }

     override fun getWeatherFromDataBaseByTimeZone(timeZone: String): WeatherResponse {


        return localSource.getWeatherFromDataBaseByTimeZone(timeZone)
    }

    override suspend fun refreshDataBase() {
        var list = localSource.getAllData()
        var favList = localSource.getAllFavourite()
        for (item in list!!) {
            getWeatherDataFromApiAndInserInDataBase(item.lat.toString(), item.lon.toString())
        }
    }

     override fun getDataForAlarm(lat: Double, lon: Double, timeZone: String): WeatherResponse {
         TODO("Not yet implemented")
     }

     override suspend fun getCurrentWeather(
        lat: String,
        lon: String,
        units: String,
        lang: String,
        exclude: String
    ): Response<WeatherResponse> {
        val response =
            remoteSource.getCurrentWeatherOverNetwork()
        return  response
        System.out.println("we are in repo")
        Log.i("respons", response.toString())
    }

}


//    @RequiresApi(Build.VERSION_CODES.M)
//    private fun isOnline(context: Context): Boolean {
//        val connectivityManager =
//            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        if (connectivityManager != null) {
//            val capabilities =
//                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
//            if (capabilities != null) {
//                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
//                    return true
//                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
//                    return true
//                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
//                    return true
//                }
//            }
//        }
//        return false
//    }



