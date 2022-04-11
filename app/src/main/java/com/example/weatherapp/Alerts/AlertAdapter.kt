package com.example.weatherapp.Alerts

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherApp.Alert.AlertModel
import com.example.weatherApp.Alert.OnDeleteAlarmClickListener
import com.example.weatherapp.R
import com.example.weatherapp.networks.Model.Alert

class AlertAdapter (var alarmList:ArrayList<AlertModel>, var onDeleteAlarmClickListener: OnDeleteAlarmClickListener) :RecyclerView.Adapter<AlertAdapter.AlarmViewModel>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewModel {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favourite_item,parent,false)

        return AlarmViewModel(view)
    }


    override fun onBindViewHolder(holder:AlarmViewModel ,position: Int) {
        Log.e(ContentValues.TAG, "onBindViewHolder: ", )
        var alarm_item = alarmList[position]
        holder.alert_datefrom.text = alarm_item.DateFrom
        holder.alert_dateto.text = alarm_item.DateTO
        holder.alert_timefrom.text = alarm_item.TimeFrom
        holder.alert_timeto.text = alarm_item.TimeTo

        holder.alerImag_more.setOnClickListener {
            onDeleteAlarmClickListener.onDeleteAlarmClicked(holder.alerImag_more,
                alarm_item.alarmId)

        }
    }

        override fun getItemCount(): Int {
            return alarmList.size    }

        fun setDataToAlarmAdapter(alarmUpdatedList: List<Alert>) {
//        alarmList.clear()
//        alarmList.addAll(alarmUpdatedList)
//        notifyDataSetChanged()

        }


    inner class AlarmViewModel(private val itemView: View) :RecyclerView.ViewHolder(itemView) {
        var alerImag: ImageView = itemView.findViewById(R.id.alarm_image)
        var alert_datefrom: TextView = itemView.findViewById(R.id.alarm_data_from)
        var alert_timefrom: TextView = itemView.findViewById(R.id.alarm_time_from)
        var alert_dateto: TextView = itemView.findViewById(R.id.alarm_Date_to)
        var alert_timeto: TextView = itemView.findViewById(R.id.alarm_time_to)
        var alerImag_more: ImageView = itemView.findViewById(R.id.more_alarm)


    }


}