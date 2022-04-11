package com.example.weatherApp.home

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import com.example.weatherApp.R
//import com.example.weatherApp.utils.Constants
import com.example.weatherapp.R
import com.example.weatherapp.Utils
import com.example.weatherapp.home.view.OnDayClickListener
import com.example.weatherapp.home.view.SHARD_NAME
import com.example.weatherapp.networks.Model.Daily
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DailyAdapter( dailyList: ArrayList<Daily>, var context: Context?, var onDayClick: OnDayClickListener) : RecyclerView.Adapter<DailyAdapter.ViewHolder>() {


   var dailyList: ArrayList<Daily> = ArrayList()
    lateinit var lang: String
    lateinit var unit: String
    lateinit var sharedPref: SharedPreferences
    lateinit var tempUnit: String
    init{
        sharedPref =context!!.getSharedPreferences(SHARD_NAME, Context.MODE_PRIVATE)

        lang = sharedPref.getString(Utils.LANG_UNIT, "en").toString()
        unit = sharedPref.getString(Utils.WIND_UNIT, "metric").toString()

        setUnits(unit)

   }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.day_item, parent, false)
      val viewHolder: ViewHolder = ViewHolder(v)

      Log.i("TAG", "=========== onCreateViewHolder ===========")
        return viewHolder     }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dailyList[position]
       val customListener: OnDayClickListener

        val calendar: Calendar = Calendar.getInstance()
     //   calendar.setTimeInMillis(dailyList[position].dt.toLong()*1000)
//     holder.day_name_details.text=getDayName(calendar.getDisplayName(
//            Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()),context )
        holder.itemView.setOnClickListener {
           // onDayClick.onDayClicked(item as Daily)

        }
        holder.cloud_day.text=item.clouds.toString()

        //Glide.with(context).load(currentMovie.imageUrl).into(holder.movieImg)
        Log.i("TAG", "=========== onBindViewHolder ===========")
    }

//
    fun getDayName(dayName:String,context:Context):String{
        return when (dayName.trim()) {
            "Saturday" ->context.getString(R.string.saturday)
            "Sunday" ->context.getString(R.string.sunday)
            "Monday" ->context.getString(R.string.monday)
            "Tuesday" ->context.getString(R.string.tuesday)
            "Wednesday" ->context.getString(R.string.wednesday)
            "Friday" ->context.getString(R.string.friday)
            "Thursday" ->context.getString(R.string.thursday)
            else ->dayName
        }
    }
    fun updateData(newDailyList: List<Daily>){
        dailyList.clear()
        dailyList.addAll(newDailyList)
        notifyDataSetChanged()
    }
    private fun timeFormat(millisSeconds: Int): String {
        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis((millisSeconds * 1000).toLong())
        val format = SimpleDateFormat("hh:mm aaa", Locale(lang.toString()))
        return format.format(calendar.time)

    }

    fun convertToArabic(value: Int): String? {
        return (value.toString() + "")
            .replace("1", "١").replace("2", "٢")
            .replace("3", "٣").replace("4", "٤")
            .replace("5", "٥").replace("6", "٦")
            .replace("7", "٧").replace("8", "٨")
            .replace("9", "٩").replace("0", "٠")
    }

    fun setUnits(unit: String) {
        when (unit) {
            "metric" -> {
                tempUnit = "°c"
            }
            "imperial" -> {
                tempUnit = "°f"
            }
            "standard" -> {
                tempUnit = "°k"
            }

        }
    }

    override fun getItemCount(): Int {
        Log.i("Adapter", "we are in daily"+dailyList.size.toString())
        return dailyList.size
    }
fun setList(days :ArrayList<Daily>){
    this.dailyList=days
    Log.i("Adapter", dailyList.size.toString())
    Log.i("Adapter", days.size.toString())



}

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      //  var movieImg: ImageView = itemView.findViewById(R.id.day_image)
//        var daydes: TextView = itemView.findViewById(R.id.day_desc)
//        var day_name_details: TextView = itemView.findViewById(R.id.day_name_details)
//        var day_tem: TextView = itemView.findViewById(R.id.day_tem)
//        var pressure_day: TextView = itemView.findViewById(R.id.pressure_day)
//        var wind_day: TextView = itemView.findViewById(R.id.wind_day)
        var cloud_day: TextView = itemView.findViewById(R.id.day_name)
//        var humidity_day: TextView = itemView.findViewById(R.id.humidity_day)
//        var sunRise_day: TextView = itemView.findViewById(R.id.sunRise_day)
//        var sun_set_day: TextView = itemView.findViewById(R.id.sun_set_day)
    }
}