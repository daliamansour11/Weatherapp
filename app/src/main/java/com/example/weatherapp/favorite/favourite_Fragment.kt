package com.example.weatherapp.favorite

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemomvvm.network.WeatherClient
import com.example.weatherApp.favorite.FavouriteAdapter
import com.example.weatherApp.favorite.FavouriteViewModel

import com.example.weatherapp.R
import com.example.weatherapp.Repository.Repository
import com.example.weatherapp.database.LocalDataSource

import com.google.android.gms.maps.MapFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton


class favourite_Fragment : Fragment(), OnFavMenuClick {

    lateinit var mlayoutManager:LinearLayoutManager
   lateinit var fragment: favourite_Fragment;
    private lateinit var  floatbutton : FloatingActionButton
    private lateinit var recyclerView : RecyclerView
    private lateinit var fav_img : ImageView
    private lateinit var  lifecycleOwner: LifecycleOwner
   private lateinit var favAdapter : FavouriteAdapter
    private lateinit var favViewModel: FavouriteViewModel
      lateinit var vfavFactory: FavouriteModelFactory





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragment = inflater.inflate(R.layout.fragment_favourite, container, false)

        floatbutton = fragment.findViewById(R.id.add_new_fav)
        recyclerView = fragment.findViewById(R.id.recyclerView)
        fav_img = fragment.findViewById(R.id.fav_img)
         lifecycleOwner =this

        favAdapter = FavouriteAdapter(this, arrayListOf(),requireContext())
        mlayoutManager = LinearLayoutManager(requireContext())
        mlayoutManager.orientation = RecyclerView.HORIZONTAL
         recyclerView.layoutManager = mlayoutManager
        recyclerView.adapter = favAdapter





        return fragment
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Getting ViewModel Ready
        vfavFactory = FavouriteModelFactory(
            Repository.getInstance(
                WeatherClient.getInstance(), LocalDataSource(view.context),
                requireContext()

            )
        )
        favViewModel = ViewModelProvider(this, vfavFactory).get(FavouriteViewModel::class.java)




        favViewModel.favouritWeather.observe(this) { respo ->


            Log.i(ContentValues.TAG, "onCreate: ${respo.toString()}")

            favAdapter.favouriteList(respo.timezone)
            favAdapter.notifyDataSetChanged()
            fav_img.imageAlpha=respo.timezoneOffset



            floatbutton.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("mapId", "fav")
                val mapFragment = MapFragment()
                mapFragment.arguments = bundle
                Navigation.findNavController(it)
                    .navigate(R.id.home_fragment, bundle);

            }
        }


    }
//    private fun initUi() {
//
//        recyclerView.apply {
//            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            adapter = favAdapter
//        }
//    }

    companion object;


    override  fun onClick(view: View,timezone:String) {
        var popup = PopupMenu(requireContext(),view);
        popup.inflate(R.menu.more_vart);
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.delete -> {
                   favViewModel.deleteFromFav(timezone)
                    favAdapter.notifyDataSetChanged()
                   showSnakBar(view)
                }

            }
            false
        }
        popup.show();


    }

    private fun showSnakBar(view: View) {

    }

}

