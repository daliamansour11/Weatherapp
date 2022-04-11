package com.example.weatherapp.home.view


    import android.content.Context
    import android.content.SharedPreferences
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.ImageView
    import android.widget.TextView
    import androidx.recyclerview.widget.RecyclerView
    import com.bumptech.glide.Glide
    //import com.example.weatherApp.utils.Constants
    import com.example.weatherapp.R
    import com.example.weatherapp.Utils
    import com.example.weatherapp.networks.Model.Hourly
    import java.text.SimpleDateFormat
    import java.util.*
    import kotlin.collections.ArrayList

class HourlyAdapter ( hourList:ArrayList<Hourly>, var context: Context): RecyclerView.Adapter<HourlyAdapter.ViewHolder>() {

    var hourList:ArrayList<Hourly> = ArrayList()
        lateinit var lang: String
        lateinit var unit: String
        lateinit var sharedPref: SharedPreferences
        lateinit var tempUnit: String
        init{
            sharedPref =context.getSharedPreferences(SHARD_NAME, Context.MODE_PRIVATE)

            lang = sharedPref.getString(Utils.LANG_UNIT, "en").toString()
            unit = sharedPref.getString(Utils.WIND_UNIT, "metric").toString()
            setUnits(unit)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val v = layoutInflater.inflate(R.layout.hour_item, parent, false)
            val viewHolder: ViewHolder = ViewHolder(v)

        return viewHolder}

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          var hourItem=hourList[position]
//            if (lang.equals("en")) {
//               holder. = timeFormat(hourItem.dt.toInt())
//                holder.view.hourTemperature.text =
//                    (hourItem.temp.toInt()).toString() + tempUnit
//            } else {
//                holder.view.hourTime.text = timeFormat(hourItem.dt.toInt())
//                holder.view.hourTemperature.text =
//                    convertToArabic((hourItem.temp.toInt())) + tempUnit
//            }


//            Glide.with(holder.view.hourImage.context)
//                .load(hourItem.weather[0].icon?.let {
//                    getImage(it)
//                })
////            .placeholder(R.drawable.sky_app_bar)
//                .into(holder.view.hourImage)
//




        }

        fun getImage(icon:String):String= "http://openweathermap.org/img/w/${icon}.png"

        fun updateData(newHourList: List<Hourly>){
            hourList.clear()
            hourList.addAll(newHourList)
            notifyDataSetChanged()
        }





        override fun getItemCount(): Int {
            return hourList.size
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

    fun hourList(hourly: List<Hourly>) {
        this.hourList = hourly as ArrayList<Hourly>

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var movieImg: ImageView = itemView.findViewById(R.id.hour_image)
        var hour_time: TextView = itemView.findViewById(R.id.hour_time)


    }}


//
//    class HourViewHolder(var view:HourItemBinding)
//        : RecyclerView.ViewHolder(view.root) {
//
//    }
