package com.example.weatherapp.networks.Model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Daily(
    @SerializedName("clouds")
    val clouds: Int = 0,
    @SerializedName("dew_point")
    val dewPoint: Double = 0.0,
    @SerializedName("dt")
    val dt: Int = 0,
    @SerializedName("feels_like")
    val feelsLike: FeelsLike = FeelsLike(),
    @SerializedName("humidity")
    val humidity: Int = 0,
    @SerializedName("moon_phase")
    val moonPhase: Double = 0.0,
    @SerializedName("moonrise")
    val moonrise: Int = 0,
    @SerializedName("moonset")
    val moonset: Int = 0,
    @SerializedName("pop")
    val pop: Double = 0.0,
    @SerializedName("pressure")
    val pressure: Int = 0,
    @SerializedName("rain")
    val rain: Double = 0.0,
    @SerializedName("sunrise")
    val sunrise: Int = 0,
    @SerializedName("sunset")
    val sunset: Int = 0,
    @SerializedName("temp")
    val temp: Temp = Temp(),
    @SerializedName("uvi")
    val uvi: Double = 0.0,
    @SerializedName("weather")
    val weather: List<WeatherX> = listOf(),
    @SerializedName("wind_deg")
    val windDeg: Int = 0,
    @SerializedName("wind_gust")
    val windGust: Double = 0.0,
    @SerializedName("wind_speed")
    val windSpeed: Double = 0.0
)