package com.example.weatherapp

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle

import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.*
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration

import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.Alerts.alertview.Alerts_Fragment
import com.example.weatherapp.favorite.favourite_Fragment
import com.example.weatherapp.home.view.HomeFragment
import com.example.weatherapp.setting.setting_Fragment

import com.google.android.material.navigation.NavigationView
import org.json.JSONObject
import java.lang.Exception
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() ,
    View.OnClickListener {
              private lateinit  var toggle:ActionBarDrawerToggle

             private lateinit var recyclerView: RecyclerView
            private lateinit var container : FragmentContainerView
            private lateinit var home_icon : ImageView
            private lateinit var drawerLayout: DrawerLayout
            private lateinit var navigation: NavigationView


     override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    home_icon = findViewById(R.id.home_icon)
    drawerLayout = findViewById(R.id.drawer_layout)
    navigation = findViewById(R.id.view_nav)
    home_icon.setOnClickListener(this)

    toggle= ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
    drawerLayout.addDrawerListener(toggle)
    toggle.syncState()
    supportActionBar?.setDisplayHomeAsUpEnabled(true)



    navigation.setNavigationItemSelectedListener {
        it.isChecked = true
        when (it.itemId) {
            R.id.home_fragment ->replaceFragment(HomeFragment(),"Home")
//                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()

            R.id.favourite_fragment -> replaceFragment(favourite_Fragment(),"Favourite")


            R.id.setting_fragment -> replaceFragment(setting_Fragment(),"Setting")
//                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()


            R.id.alerts_fragment ->replaceFragment(Alerts_Fragment(),"Alert")
               // Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()


        }
        true
    }
}


    private fun replaceFragment(fragment:Fragment,tittle: String){
        val fragmentManager= supportFragmentManager
        val FragmentTransaction=  fragmentManager.beginTransaction()
        FragmentTransaction.replace(R.id.nav_host_fragment,fragment)
        FragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)


}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when (p0.id) {
                R.id.home_icon ->drawerLayout .openDrawer(Gravity.LEFT)
            }
        }
    }
    }


//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//
//        when (item.itemId) {
//            R.id.home_fragment -> {
//                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
//                supportFragmentManager.beginTransaction().replace(container.getId(), HomeFragment())
//                    .commit()
//            }
//            R.id.favourite_fragment -> {
//                Toast.makeText(this, "favourite", Toast.LENGTH_SHORT).show()
//                supportFragmentManager.beginTransaction()
//                    .replace(container.getId(), favourite_Fragment()).commit()
//            }
//            R.id.setting_fragment -> {
//                Toast.makeText(this, "Add setting", Toast.LENGTH_SHORT).show()
//                supportFragmentManager.beginTransaction().replace(container.getId(), setting_Fragment()).commit()
//            }
//            R.id.alerts_fragment -> {
//                Toast.makeText(this, "Edit alert", Toast.LENGTH_SHORT).show()
//                supportFragmentManager.beginTransaction().replace(container.getId(), Alerts_Fragment()).commit()
//
//            }
//        }
//
//
//
//
//        return true
//    }




