package com.example.weatherApp.dilog

import dagger.Binds
import dagger.Module

//@Module
//@InstallIn(SingletonComponent::class)
interface OnOkClickListener {

    fun onButtonOkClicked(type:String)
}