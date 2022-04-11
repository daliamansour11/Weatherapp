package com.example.weatherapp

import android.annotation.SuppressLint
import android.content.Context
//import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.weatherApp.dilog.UserDialogActivity
import com.example.weatherapp.home.view.LOCAION_LOG
import com.example.weatherapp.home.view.LOCATION_LAT
import com.example.weatherapp.home.view.SHARD_NAME

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lateinit var sharedPreferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor
//        @SuppressLint("ResourceType")

        sharedPreferences = this.getSharedPreferences(SHARD_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        Handler(Looper.myLooper()!!).postDelayed({
            if (sharedPreferences.getString(LOCATION_LAT, "")
                    .isNullOrEmpty() || sharedPreferences.getString(
                    LOCAION_LOG, ""
                ).isNullOrEmpty()
            ) {
                val intent = Intent(this, UserDialogActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, 2000)
    }}

