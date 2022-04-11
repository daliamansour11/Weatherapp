package com.example.weatherapp.networks.Model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Minutely(
    @SerializedName("dt")
    val dt: Int = 0,
    @SerializedName("precipitation")
    val precipitation: Int = 0
)