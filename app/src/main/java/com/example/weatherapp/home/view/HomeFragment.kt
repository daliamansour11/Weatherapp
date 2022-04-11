package com.example.weatherapp.home.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.content.res.Resources
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.roomdemomvvm.network.WeatherClient
import com.example.weatherApp.home.DailyAdapter
import com.example.weatherapp.R
import com.example.weatherapp.Repository.Repository
import com.example.weatherapp.Utils
import com.example.weatherapp.database.LocalDataSource
import com.example.weatherapp.home.ViewModel.HomeModelFactory
import com.example.weatherapp.home.ViewModel.HomeModelView
import com.example.weatherapp.networks.Model.Daily

import com.example.weatherapp.networks.Model.WeatherResponse
import com.google.android.gms.location.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

const val LOCATION_LAT = "lat"
const val LOCAION_LOG = "lon"
const val SHARD_NAME = "shard"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() ,OnDayClickListener {
//    val City: String = "Alexandria"

    //    val API: String = " ba449d6d74fd1ecd621ab258d8e9f805"
    private val PERMISSION_ID = 55
    lateinit var vmFactory: HomeModelFactory
    //lateinit var recyclerView: RecyclerView
    lateinit var viewModel: HomeModelView
    lateinit var img: ImageView
    lateinit var mlayoutManager: LinearLayoutManager
    lateinit var lang: String
    lateinit var unit: String
    lateinit var tempUnit: String
    lateinit var windSpeedUnit: String
    lateinit var progress: ProgressBar
    lateinit var job: Job
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    var locationLat: Double = 0.0
    var locationLon: Double = 0.0
    lateinit var hourlyAdapter: HourlyAdapter
    lateinit var dailyAdapter: DailyAdapter
    lateinit var sharedPref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var weather_card: CardView
    lateinit var weatherLastCard: CardView
    lateinit var edit_location_linear: LinearLayout
    lateinit var location: TextView
    lateinit var  chooselocation :ImageView
    lateinit var st: TextView
    lateinit var statu: ImageView
    lateinit var tem: TextView
    lateinit var hum: TextView
    lateinit var humidity_home: TextView
    lateinit var windHome: TextView
    lateinit var pressureHome: TextView
    lateinit var cloudHome: TextView
    lateinit var sunRiseHome: TextView
    lateinit var hourText: TextView
    lateinit var sunSetHome: TextView
    lateinit var days_recycler: RecyclerView
    lateinit var hours_recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mFusedLocationClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())

        sharedPref = requireActivity().getSharedPreferences(SHARD_NAME, Context.MODE_PRIVATE)
        editor = sharedPref.edit()


        firstTime()
        lang = sharedPref.getString(Utils.LANG_UNIT, "en").toString()
        unit = sharedPref.getString(Utils.WIND_UNIT, "metric").toString()
        setLocale(lang)

        //setUnits(unit)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(R.layout.fragment_home, container, false)
        progress = fragment.findViewById(R.id.progress)
        weatherLastCard = fragment.findViewById(R.id.weather_last_card)
        weather_card = fragment.findViewById(R.id.weather_last_card)

        edit_location_linear = fragment.findViewById(R.id.edit_location_linear)
        hourText = fragment.findViewById(R.id.hour_text)
        hours_recycler =fragment.findViewById(R.id.hours_recycler)
        days_recycler =fragment.findViewById(R.id.days_recycler)
        location = fragment.findViewById(R.id.location)
        humidity_home = fragment.findViewById(R.id.humidity_home)
        st = fragment.findViewById(R.id.st)
        statu = fragment.findViewById(R.id.statu)
        sunRiseHome = fragment.findViewById(R.id.sunRise_home)
        sunSetHome = fragment.findViewById(R.id.sun_set_home)
        cloudHome = fragment.findViewById(R.id.cloud_home)
        pressureHome = fragment.findViewById(R.id.pressure_home)
        tem = fragment.findViewById(R.id.tem)
        windHome = fragment.findViewById(R.id.wind_home)

       chooselocation = fragment.findViewById(R.id.chose_location)
//
        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Getting ViewModel Ready
        vmFactory = HomeModelFactory(
            Repository.getInstance(
                WeatherClient.getInstance(), LocalDataSource(view.context),
                requireContext()

            )
        )
        viewModel = ViewModelProvider(this, vmFactory).get(HomeModelView::class.java)
        viewModel.getCurrentWeather()
        initUI()

        //Getting Recycler View related items ready

        dailyAdapter = DailyAdapter(arrayListOf(), requireContext(), this)
        mlayoutManager = LinearLayoutManager(requireContext())
        mlayoutManager.orientation = RecyclerView.HORIZONTAL
        days_recycler.layoutManager = mlayoutManager
        days_recycler.adapter = dailyAdapter

        hourlyAdapter = HourlyAdapter(arrayListOf(), requireContext())
        mlayoutManager = LinearLayoutManager(requireContext())
        mlayoutManager.orientation = RecyclerView.HORIZONTAL
        hours_recycler.layoutManager = mlayoutManager
        hours_recycler.adapter = hourlyAdapter


        //Observe exposed data of viewModel
        viewModel.onlineMovies.observe(this) { respo ->
            Log.i(TAG, "onChanged: ")
            days_recycler.visibility = View.VISIBLE
            hours_recycler.visibility =View.VISIBLE

            progress.visibility = View.GONE

            Log.i(TAG, "onCreate: ${respo.toString()}")
             // dailyAdapter.dailyList= respo.daily as ArrayList<Daily>
            dailyAdapter.setList(respo.daily as ArrayList<Daily>)
            hourlyAdapter.hourList(respo.hourly)
            dailyAdapter.notifyDataSetChanged()
            hourlyAdapter.notifyDataSetChanged()
            location.text = respo.timezone
            tem.text= respo.current.temp.toString()
            st.text=respo.current.dt.toString()
            statu.tag=respo.current
            cloudHome.text=respo.current.clouds.toString()
            pressureHome.text=respo.current.pressure.toString()
            sunSetHome.text=respo.current.sunset.toString()
            sunRiseHome.text=respo.current.sunrise.toString()
            windHome.text= respo.current.windSpeed.toString()
            humidity_home.text=respo.current.humidity.toString()
            hourText.text =respo.current.dewPoint.toString()
            chooselocation.imageAlpha = respo.timezoneOffset





        }

//        hourlyAdapter = HourlyAdapter(arrayListOf(), requireContext())
//        dailyAdapter = DailyAdapter(arrayListOf(), requireContext(), this)
        mFusedLocationClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        if (sharedPref.getString(LOCATION_LAT, "").isNullOrEmpty() || sharedPref.getString(
                LOCAION_LOG,
                ""
            ).isNullOrEmpty()
        ) {
            progress.visibility = View.VISIBLE
            weather_card.visibility = View.VISIBLE
            weatherLastCard.visibility = View.VISIBLE
            location.visibility = View.VISIBLE
            edit_location_linear.visibility = View.VISIBLE
            hourText.text = null
            //getLastLocation()
        } else {

            viewModel.onlineMovies
                .observe(viewLifecycleOwner, Observer {
                    if (it != null) {


                        updateUi(WeatherResponse())

                    } else {


                    }
                })

        }


    }




    fun updateUi(weather: WeatherResponse) {
        progress.visibility = View.GONE

        weather_card.visibility = View.VISIBLE
        weatherLastCard.visibility = View.VISIBLE
        edit_location_linear.visibility = View.VISIBLE
        location.visibility = View.VISIBLE
        weather.let {
            weather.apply {
                location.text = convertTimezone(it)
                st.text = it.current.weather[0].description


                Glide.with(requireActivity())
                    .load("http://openweathermap.org/img/w/" + current!!.weather.get(0).icon + ".png")
                    .into(statu)
                tem.text = it.current.temp.toString() + "°"
                humidity_home.text = current.humidity.toString()
                pressureHome.text = current.pressure.toString()
                windHome.text = current.windSpeed.toString()
                cloudHome.text = current.clouds.toString()

                sunRiseHome.text = timeFormat(it.current.sunrise)
                sunSetHome.text = timeFormat(it.current.sunset)
                //date.text = dateFormat(it.current.dt)
                dailyAdapter.updateData(daily)
                hourlyAdapter.updateData(hourly)



                if (lang.equals("en")) {
                    humidity_home.text = it.current.humidity.toString() + "%"
                    pressureHome.text = it.current.pressure.toString() + " hPa"
                    windHome.text = it.current.windSpeed.toString() + " " + windSpeedUnit
                    tem.text = (it.current.temp.toInt()).toString() + tempUnit
                    cloudHome.text = it.current.clouds.toString()

                } else {
                    cloudHome.text = convertToArabic(it.current.clouds)
                    humidity_home.text = convertToArabic(it.current.humidity) + "%"
                    pressureHome.text = convertToArabic(it.current.pressure) + "hPa"
                    windHome.text =
                        convertToArabic(it.current.windSpeed.toInt()) + windSpeedUnit
                    tem.text = convertToArabic(it.current.temp.toInt()) + tempUnit


                }
            }
        }

    }

    fun dateFormat(milliSeconds: Int): String {
        val calendar: Calendar = Calendar.getInstance()
        calendar.setTimeInMillis(milliSeconds.toLong() * 1000)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)
        var year = calendar.get(Calendar.YEAR)
        if (lang.equals("en")) {
            return day.toString() + "/" + month + "/" + year
        } else {
            return convertToArabic(day) + "/" + convertToArabic(month) + "/" + convertToArabic(
                year
            )
        }

    }

    fun timeFormat(millisSeconds: Int): String {
        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis((millisSeconds * 1000).toLong())
        if (lang.equals("en")) {
            val format = SimpleDateFormat("hh:00 aaa")
            return format.format(calendar.time)
        } else {
            val format = SimpleDateFormat("۰۰:hh aaa")
            return format.format(calendar.time)
        }
    }

    fun setUnits(unit: String) {
        when (unit) {
            "metric" -> {
                tempUnit = "°c"
                windSpeedUnit = "m/s"
            }
            "imperial" -> {
                tempUnit = "°f"
                windSpeedUnit = "m/h"
            }
            "standard" -> {
                tempUnit = "°k"
                windSpeedUnit = "m/s"
            }

        }
    }


    fun setLocale(languageCode: String?) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources: Resources = requireActivity().resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    fun convertToArabic(value: Int): String? {
        return (value.toString() + "")
            .replace("1", "١").replace("2", "٢")
            .replace("3", "٣").replace("4", "٤")
            .replace("5", "٥").replace("6", "٦")
            .replace("7", "٧").replace("8", "٨")
            .replace("9", "٩").replace("0", "٠")
    }

    fun convertTimezone(weatherResponse: WeatherResponse): String {
        var arabicTimezone = ""
        var addressList: List<Address>? = null

        val geocoder = Geocoder(context, Locale(lang))
        try {
            addressList = geocoder.getFromLocation(weatherResponse.lat, weatherResponse.lon, 1)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        if (addressList?.size!! > 0) {
            val address = addressList!![0]

            arabicTimezone = address.adminArea
        }
        return arabicTimezone
    }

    private fun firstTime() {
        var isFirst = sharedPref.getBoolean("isFirstTimeLaunch", true)
        if (isFirst) {

            editor.putString(
                Utils.LANG_UNIT, "en"
            )
            editor.putString(Utils.WIND_UNIT, "metric")
            editor.putBoolean("isFirstTimeLaunch", false)
            editor.commit()
            // getLastLocation()
        }
    }

    private fun getCurrentTime(): String {
        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z")
        val currentDateAndTime: String = simpleDateFormat.format(Date())

        return currentDateAndTime
    }

//    @SuppressLint("MissingPermission")
//    private fun getLastLocation() {
//        if (checkPermissions()) {
//            if (isLocationEnabled()) {
//
//                mFusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
//                    val location: Location? = task.result
//                    if (location == null) {
//                        requestNewLocationData()
//                    } else {
//
//                        locationLat = location?.latitude!!
//                        locationLon = location?.longitude!!
//                        editor.putString(LOCATION_LAT, locationLat.toString())
//                        editor.putString(LOCAION_LOG, locationLon.toString())
//                        editor.commit()
//
//
//                        viewModel.getCurrentWeather(
//                            sharedPref.getString(LOCATION_LAT, "")!!.toDouble(),
//                            sharedPref.getString(
//                                LOCAION_LOG, ""
//                            )!!.toDouble()
//                        )
//                        homeViewModel.getWeather
//                            .observe(viewLifecycleOwner, Observer {
//                                if (it != null) {
//
//                                    updateUi(it)
//                                }
//                            })
//
//                    }
//                }
//            } else {
//                val firstTimeLaunch = sharedPref.getBoolean("isFirstTimeLaunch", true)
//                if (firstTimeLaunch) {
//                    val firstTimeLocationEnabled =
//                        sharedPref.getBoolean("isFirstTimeLocationEnabled", true)
//                    if (firstTimeLocationEnabled) {
//
//                    }
//                }
//                locationNotEnable()
//            }
//        } else {
//            requestPermissions()
//        }
//
//    }

    private fun locationNotEnable() {

        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        ActivityCompat.startActivityForResult(requireActivity(), intent, PERMISSION_ID, Bundle())
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getActivity()?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            )
        ) {
            editor.putBoolean("isFirstTimeLocationEnabled", true)
            return true
        } else {
            return false;
        }
    }

    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    private fun requestPermissions() {
//
        requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ), PERMISSION_ID
        )
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        Looper.myLooper()?.let {
            mFusedLocationClient!!.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                it
            )
        }
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
            locationLat = mLastLocation?.latitude!!
            locationLon = mLastLocation?.longitude!!

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        Log.e(ContentValues.TAG, "onRequestPermissionsResult: ")
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // getLastLocation()
            }
        }
    }

    override fun onDayClicked(day: Daily) {
        TODO("Not yet implemented")
    }

    private fun initUI() {
//        recyclerView = findViewById(R.id.recyclerView)
//
//        val _movieList = MutableLiveData<List<WeatherResponse>>()
//
//        init {
//            Log.i(TAG, "instance initializer: Creation of ViewModel")
//            getCrrentWeather()
//        }
//
//        //Expose returned online Data
//        val onlineMovies: LiveData<List<WeatherResponse>> = _WeatherResponse


//        fun getCurrentWeather() {
//            viewModelScope.launch {
//                var respo = iRepo.getWeatherDataFromApiAndInserInDataBase("31.205753", "29.924526")
//                System.out.println("respo")
//                withContext(Dispatchers.Main) {
//                    //expose data to main
//                    Log.i(TAG, "getCurrentWeather: ${respo}")
//                }
//            }
//
//        //}
    }
}







