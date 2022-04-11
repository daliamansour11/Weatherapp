package com.example.weatherApp.alarm.setAlarmDialog

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager.TAG
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager

import com.example.weatherapp.R
import com.google.android.gms.maps.MapFragment
import java.util.*
import java.util.concurrent.TimeUnit

class SetAlarmsFragment : Fragment() {
//    lateinit var binding: FragmentSetAllarmBinding
//    private val alarmDialogViewModel: AlarmDialogViewModel by viewModels()
//    lateinit var mTimePicker: TimePickerDialog
//    lateinit var datePickerDialog: DatePickerDialog
//    lateinit var startDate:String
//    lateinit var startTime:String
//    lateinit var endDate:String
//    lateinit var endTime:String
//    var latitude: Double = 0.0
//    var longitude: Double = 0.0
//    private var calStart = Calendar.getInstance()
//    private var calEnd = Calendar.getInstance()
//    lateinit var timeZone:String
//    lateinit var navControler: NavController
//    val calender = Calendar.getInstance()
//    var count=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val setAlrlm =inflater.inflate(R.layout.fragment_set_alarms, container, false)
//        binding.lifecycleOwner=this
//        binding.viewModel=alarmDialogViewModel
//        return binding.root
        return  setAlrlm
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        navControler = Navigation.findNavController(view)
//        if (arguments != null) {
//            latitude = arguments?.getString("lat")!!.toDouble()
//            longitude = arguments?.getString("lon")!!.toDouble()
//            timeZone = arguments?.getString("timeZone")!!
//            binding.selectedPalce.text=timeZone
//        }
//        binding.fromDateIamge.setOnClickListener {
//            openPickerDate("from")
//        }
//
//        binding.dateToIamge.setOnClickListener {
//            openPickerDate("to")
//        }
//        binding.selectedPalce.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString("mapId", "alarm")
//            val mapFragment = MapFragment()
//            mapFragment.arguments = bundle
//            navControler.navigate(R.id.mapFragment,bundle)
//
//        }
//        binding.submit.setOnClickListener {
//          if(validData()) {
//              addAlarmToDataBase()
//              count++
//              setAlarm(
//                  requireContext(),
//                  count,
//                  calStart,
//                  calEnd,
//                  timeZone,
//                  latitude,
//                  longitude,
//                  true
//              )
//
//              Navigation.findNavController(it)
//                  .navigate(R.id.action_setAlarmsFragment_to_alarmFragment);
//          }
//          else{
//               Toast.makeText(requireContext(),"please Enter valid Date",Toast.LENGTH_LONG).show()
//
//          }
//        }
//        binding.btnCancel.setOnClickListener {
//
//        }
    }







//    private fun setAlarm(
//        context: Context,
//        id: Int,
//        calStart: Calendar,
//        calEnd: Calendar,
//        timeZone: String,
//        lat:Double,
//        lon:Double,
//        sound: Boolean
//    ) {
//        println("in fragment")
//        val data = Data.Builder()
//            .putString("time", timeZone)
//            .putInt("ID", id)
//            .putDouble("lat",lat)
//            .putDouble("lon",lon)
//            .build()
//
////
////        val periodicWorkRequest = PeriodicWorkRequest.Builder(
////            AlarmRepetedWorkManger::class.java,
////            24, TimeUnit.HOURS
////        )
////            .setInputData(data)
////            .addTag(id.toString())
////            .build()
////        WorkManager.getInstance(requireContext()).enqueueUniquePeriodicWork(
////            id.toString(), ExistingPeriodicWorkPolicy.REPLACE
////            ,periodicWorkRequest
////        )
//    }
//
//
//
//
//    private fun addAlarmToDataBase() {
//        if(validData()) {
////            var weatherAlarm=
////                AlarmModel(startDate,startTime,endDate,endTime,timeZone,latitude,longitude,0)
////            alarmDialogViewModel.insertAlarmIntoDataBase(weatherAlarm)
//        }
//    }
//    private fun validData(): Boolean {
////        val currentTime = Calendar.getInstance().getTime();
////        val formatter = SimpleDateFormat("MM/dd/yyyy hh:mm:ss a")
////        val currantData: String = formatter.format(currentTime)
////
//
//        if (startTime.equals("") || endTime.equals("") ||endDate.equals("")||startDate.equals("")) {
//            Toast.makeText(context, "Date is required :", Toast.LENGTH_LONG).show()
//            return false
//        }
////         else if (startDate<currentData||endDate<currentData){
////            Toast.makeText(context, " please Enter Invalid Date and Time :", Toast.LENGTH_LONG).show()
////
////        }
//        return true
//    }
//
//    companion object {
//
//    }
//    fun openPickerTime(timeType:String){
//        val hour = calender.get(Calendar.HOUR_OF_DAY)
//        val minute = calender.get(Calendar.MINUTE)
//
//        mTimePicker = TimePickerDialog(context,
//            { view, hourOfDay, minute
//                ->
//                if(timeType.equals("from")){
////                    calStart.set(Calendar.DAY_OF_YEAR,hourOfDay)
////                    calStart.set(Calendar.MINUTE,minute)
////                    binding.timeFromPicker.text=
////                        (String.format("%d : %d", hourOfDay, minute))
//                    startTime=(String.format("%d : %d", hourOfDay, minute))
//                    binding.datePicker.text=startDate.plus("\n").plus(startTime)
//
//
//                }
//                else if (timeType.equals("to")){
////                    calEnd.set(Calendar.DAY_OF_YEAR,hourOfDay)
////                    calEnd.set(Calendar.MINUTE,minute)
////                    binding.timeToPicker.text=
////                        (String.format("%d : %d", hourOfDay, minute))
//                    endTime=(String.format("%d : %d", hourOfDay, minute))
//                    binding.dateToPicker.text=endDate.plus("\n").plus(endTime)
//
//
//                }
//
//            }
//            , hour, minute, false)
//        mTimePicker.show()
//
//    }
//
//    fun openPickerDate(dateType:String){
//        val calendar = Calendar.getInstance()
//        val year = calendar[Calendar.YEAR]
//        val month = calendar[Calendar.MONTH]
//        val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
//
//        datePickerDialog = DatePickerDialog(requireContext(),
//            { datePicker, year, month, day ->
//                if (dateType.equals("from")){
////                    calStart.set(year,month,day)
////                    binding.datePicker.text=("$day/$month/$year")
//                    startDate=("$day/$month/$year")
//
//                }
//                else if (dateType.equals("to")){
////                    calEnd.set(year,month,day)
////                    binding.dateToPicker.text=("$day/$month/$year")
//                    endDate=("$day/$month/$year")
//                }
//                openPickerTime(dateType)
//            }, year, month, dayOfMonth
//        )
//        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
//        datePickerDialog.show()
//
//
//    }
//
//
//
//    private fun getEvent(){
//
//
//    }

}