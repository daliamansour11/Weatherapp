package com.example.weatherApp.dilog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

import com.example.weatherapp.MainActivity
import androidx.fragment.app.FragmentTransaction
import com.example.weatherapp.maps.map_Fragment


class UserDialogActivity : AppCompatActivity() {

//   private val dialogViewModel:UserDialogViewModel
         lateinit var gpsbtn: RadioButton
         lateinit var mapbtn: RadioButton

    lateinit var Okbtn : Button
    lateinit var lifecycleOwner: LifecycleOwner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( com.example.weatherapp.R.layout.activity_user_dialog)
        Okbtn= findViewById(com.example.weatherapp.R.id.btn_ok)
        gpsbtn= findViewById(com.example.weatherapp.R.id.gps_btn)
        mapbtn= findViewById(com.example.weatherapp.R.id.map_btn)
        lifecycleOwner=this
          Okbtn.setOnClickListener {
            if(gpsbtn.isChecked){

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else if (mapbtn.isChecked){
                //open map fragment
                println("map")
                val newFragment: Fragment = map_Fragment()
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()


                transaction.replace(com.example.weatherapp.R.id.mapfragmentContainer, map_Fragment())
                transaction.addToBackStack(null)
                transaction.commit()


            }
            else{

                println("no")
            }
        }
    }
}