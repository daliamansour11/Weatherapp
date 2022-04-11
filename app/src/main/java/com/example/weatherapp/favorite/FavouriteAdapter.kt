package com.example.weatherApp.favorite

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

import com.example.weatherapp.R
import com.example.weatherapp.favorite.FavouriteModel
import com.example.weatherapp.favorite.favourite_Fragment
import com.example.weatherapp.home.view.LOCAION_LOG
import com.example.weatherapp.home.view.LOCATION_LAT
import com.example.weatherapp.home.view.SHARD_NAME

class FavouriteAdapter(
    var onFavMenuClick: favourite_Fragment,
     favouriteList:ArrayList<FavouriteModel>,
    var context:Context) : RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {
    var favouriteList:ArrayList<FavouriteModel> = ArrayList()
    lateinit var prefs: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    init {
        prefs  =context.getSharedPreferences(SHARD_NAME, Context.MODE_PRIVATE)

        editor= prefs.edit()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favourite_item,parent,false)

        return FavouriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        var fav_item=favouriteList[position]

        holder.country_favname.text=fav_item.timeZone
        holder.more_fav.setOnClickListener {

            onFavMenuClick.onClick(holder.more_fav,fav_item.timeZone!!)
        }

            holder.more_fav.setOnClickListener {

            onFavMenuClick.onClick(holder.more_fav,fav_item.timeZone!!)
        }
        holder.fav_layout.setOnClickListener {
            editor.putString(LOCATION_LAT,fav_item.lat.toString())
            editor.putString(LOCAION_LOG ,fav_item.lon.toString())
            editor.commit()
//            Navigation.findNavController(it).navigate(R.id.action_favoriteFragment_to_homeFragment);

        }
    }

    override fun getItemCount(): Int {
        return favouriteList.size
    }

    fun setData(it:List<FavouriteModel>) {

            favouriteList.clear()
            favouriteList.addAll(it)
            notifyDataSetChanged()

    }


    fun favouriteList(arrayList: String) {
        this.favouriteList= arrayListOf()
        Log.i("Adapter",favouriteList .size.toString())


    }

    inner class FavouriteViewHolder(private val itemView: View) :RecyclerView.ViewHolder(itemView){
        var fav_layout:ConstraintLayout = itemView.findViewById(R.id.fav_layout)
        var country_favname: TextView = itemView.findViewById(R.id.country_fav_name)
        var more_fav: TextView = itemView.findViewById(R.id.more_fav)



    }


}