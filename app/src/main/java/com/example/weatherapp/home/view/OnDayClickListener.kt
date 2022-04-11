package com.example.weatherapp.home.view

import com.example.weatherapp.networks.Model.Daily


interface OnDayClickListener {
    fun onDayClicked(day: Daily)
}