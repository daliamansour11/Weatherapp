package com.example.weatherapp.networks.Model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Alert(
    @SerializedName("description")
    val description: String = "",
    @SerializedName("end")
    val end: Int = 0,
    @SerializedName("event")
    val event: String = "",
    @SerializedName("sender_name")
    val senderName: String = "",
    @SerializedName("start")
    val start: Int = 0,
    @SerializedName("tags")
    val tags: List<String> = listOf()
)