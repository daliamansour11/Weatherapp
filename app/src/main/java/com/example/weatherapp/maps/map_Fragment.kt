package com.example.weatherapp.maps

import android.content.Context
import android.content.SharedPreferences
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.weatherapp.R
import com.example.weatherapp.favorite.FavouriteModel
import com.example.weatherapp.home.view.LOCAION_LOG
import com.example.weatherapp.home.view.LOCATION_LAT
import com.example.weatherapp.home.view.SHARD_NAME
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.IOException
import java.util.*

class map_Fragment : Fragment() {

    private var mapId: String ?= null
    var userLocationMarker: Marker? = null
    lateinit var btn_float: FloatingActionButton
    lateinit var root:View
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var timeZone:String=""
    lateinit var navControler: NavController
    private val mapViewModel: MapViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val mapfra = inflater.inflate(R.layout.fragment_map_, container, false)
         btn_float= mapfra.findViewById(R.id.add_flout_btn)
        return  mapfra
    }
    companion object {

            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapId = arguments?.getString("mapId")!!
        navControler = Navigation.findNavController(view)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        sharedPreferences = requireActivity().getSharedPreferences(SHARD_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        btn_float.setOnClickListener {

            if(mapId.equals("hom")){
                editor.putString(LOCATION_LAT, latitude.toString())
                editor.putString(LOCAION_LOG, longitude.toString())
                editor.commit()
                navControler.navigate(R.id.home_fragment)



            }
            else if (mapId.equals("fav")){
                getTimeZone(latitude,longitude)
                var newFavPlace= FavouriteModel(timeZone,latitude,longitude)
                mapViewModel.insertFavPlaceInDataBase(newFavPlace)

                navControler.navigate(R.id.favourite_fragment)


            }
            else if (mapId.equals("alarm")){
                getTimeZone(latitude,longitude)

                if(timeZone.isNullOrEmpty()){
                    Toast.makeText(requireContext()," try again", Toast.LENGTH_LONG).show()

                }
                else {
                    val bundle = Bundle()
                    bundle.putString("lat", latitude.toString())
                    bundle.putString("lon", longitude.toString())
                    bundle.putString("timeZone", timeZone)
//
                }
            }
        }

    }
    private fun getTimeZone(lat:Double,lon:Double) {
        val geocoder: Geocoder
        val addresses: List<Address>?
        geocoder = Geocoder(context, Locale.getDefault())

        try {
            addresses = geocoder.getFromLocation(lat, lon, 1)
            if (addresses != null) {
                val address = addresses[0]
                val city = address.locality
                val state = address.adminArea
                val country = address.countryName
                timeZone= "$state $city $country".trimIndent()


            }

        }
        catch (e: IOException) {

        }

    }

    private val callback = OnMapReadyCallback { googleMap ->
        googleMap.setOnMapClickListener {
            googleMap.clear()
            googleMap.addMarker(MarkerOptions().position(it))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(it))
            latitude = it.latitude
            longitude = it.longitude


        }




    }
    }
