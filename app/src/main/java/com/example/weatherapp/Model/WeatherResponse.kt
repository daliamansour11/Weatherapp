package com.example.weatherapp.networks.Model


import androidx.annotation.Keep
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Keep
@Entity(tableName ="CurrentWeather")
data class WeatherResponse(
    @SerializedName("alerts")
    val alerts: List<Alert> = listOf(),
    @SerializedName("current")
    val current: Current = Current(),
    @SerializedName("daily")
    val daily: List<Daily> = listOf(),
    @SerializedName("hourly")
    val hourly: List<Hourly> = listOf(),
    @SerializedName("lat")
    val lat: Double = 0.0,
    @SerializedName("lon")
    val lon: Double = 0.0,
    @SerializedName("minutely")
    val minutely: List<Minutely> = listOf(),
    @SerializedName("timezone")
    val timezone: String = "",
    @SerializedName("timezone_offset")
    val timezoneOffset: Int = 0
)