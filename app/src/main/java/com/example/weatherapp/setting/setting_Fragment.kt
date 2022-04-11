package com.example.weatherapp.setting

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import com.example.weatherapp.MainActivity
import com.example.weatherapp.R
import com.example.weatherapp.Utils.LANG_UNIT
import com.example.weatherapp.Utils.LOCATION_UNIT
import com.example.weatherapp.Utils.TEMP_UNIT
import com.example.weatherapp.Utils.WIND_UNIT
import com.example.weatherapp.home.view.SHARD_NAME
import java.util.*


class setting_Fragment : Fragment() {
   lateinit var location :RadioButton
   lateinit var btnEnglish :RadioButton
   lateinit var btnArabic :RadioButton
   lateinit var btnCelsius :RadioButton
   lateinit var btnKelvin :RadioButton
   lateinit var btnFahrenheit :RadioButton
   lateinit var btnGps :RadioButton
   lateinit var btnMap :RadioButton
   lateinit var btnMeter :RadioButton
   lateinit var btnMileHour :RadioButton
    lateinit var sharedPref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var  settingViewModel: SettingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref = requireActivity().getSharedPreferences(SHARD_NAME, Context.MODE_PRIVATE)
        editor = sharedPref.edit()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val settindfra =inflater.inflate(R.layout.fragment_setting_, container, false)
        btnArabic = settindfra.findViewById(R.id.arabic)
        btnEnglish = settindfra.findViewById(R.id.english)
        btnGps = settindfra.findViewById(R.id.gps)
        btnMap = settindfra.findViewById(R.id.map)
        btnCelsius = settindfra.findViewById(R.id.celsius)
        btnFahrenheit = settindfra.findViewById(R.id.fahrenheit)
        btnKelvin = settindfra.findViewById(R.id.kelvin)
        btnMeter = settindfra.findViewById(R.id.meter_second)
        btnMileHour = settindfra.findViewById(R.id.mile_hour)


        return settindfra



    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var lang = sharedPref.getString(LANG_UNIT, "en")
        var tempUnit = sharedPref.getString(TEMP_UNIT, "celsius")
        var windUnit = sharedPref.getString(WIND_UNIT, "metric")
        var locationUnit = sharedPref.getString(LOCATION_UNIT, "gps")
        when (lang) {
            "en" ->btnArabic.isChecked = true
            "ar" -> btnArabic.isChecked = true
        }
        when (tempUnit) {
            "metric" -> btnCelsius.isChecked = true
            "standard" -> btnKelvin.isChecked = true
            "imperial" -> btnFahrenheit.isChecked = true
        }
        when (locationUnit) {
            "gps" -> btnGps.isChecked = true
            "map" -> btnMap.isChecked = true

        }
        when (windUnit) {
            "metric" -> {
                btnMeter.isChecked = true
            }
            "imperial" -> btnMileHour.isChecked = true
        }

        btnArabic.setOnClickListener {
            changeLang("ar")
        }
        btnEnglish.setOnClickListener {
            changeLang("en")
        }
        btnCelsius.setOnClickListener {
            changeTempUnit("metric")
        }
        btnFahrenheit.setOnClickListener {
        //    changeTempUnit("imperial")
        }
        btnKelvin.setOnClickListener {
            changeTempUnit("standard")
        }
        btnMeter.setOnClickListener {
            changeWindUnit("metric")
        }
        btnMileHour.setOnClickListener {
            changeWindUnit("imperial")
        }
        btnMap.setOnClickListener {
            changeLocationUnit("gps")
        }
        btnGps.setOnClickListener {
            changeLocationUnit("map")
        }
    }

    companion object {


    }
    private fun changeLang(lang:String){
        editor.putString(LANG_UNIT,lang)
        editor.commit()
        setLocale(lang)
        settingViewModel.refreshData()
        restartApp()
    }
    private fun changeLocationUnit(unit:String){
        editor.putString(LOCATION_UNIT,unit)
        editor.commit()
        settingViewModel.refreshData()
        restartApp()
    }
    private fun changeWindUnit(unit:String){
        editor.putString(WIND_UNIT,unit)
        editor.commit()
        settingViewModel.refreshData()
        restartApp()
    }
    private fun changeTempUnit(unit:String){
        editor.putString(TEMP_UNIT,unit)
        editor.commit()
        settingViewModel.refreshData()
        restartApp()
    }

    private fun restartApp()
    {
        val intent = Intent(context, MainActivity::class.java)
        activity?.finish()
        startActivity(intent)


    }
    fun setLocale(languageCode: String?) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources: Resources = requireActivity().resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        config.setLayoutDirection(locale)
        Locale.setDefault(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        restartApp()
    }


            }

