package com.example.weatherapp.networks.Model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class FeelsLike(
    @SerializedName("day")
    val day: Double = 0.0,
    @SerializedName("eve")
    val eve: Double = 0.0,
    @SerializedName("morn")
    val morn: Double = 0.0,
    @SerializedName("night")
    val night: Double = 0.0
)