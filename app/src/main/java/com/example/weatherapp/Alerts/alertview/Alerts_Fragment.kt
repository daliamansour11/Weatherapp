package com.example.weatherapp.Alerts.alertview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherApp.Alert.OnDeleteAlarmClickListener
import com.example.weatherapp.Alerts.AlertAdapter
import com.example.weatherapp.Alerts.AlertViewModel.AlertViewModel
import com.example.weatherapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Alert_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Alerts_Fragment : Fragment(),OnDeleteAlarmClickListener {
    lateinit var alertViewModel: AlertViewModel
    lateinit var alertAdapter: AlertAdapter
    lateinit var backlmg : ImageView
    lateinit var recyclerViewalert: RecyclerView
    lateinit var addatert :FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val Alert= inflater.inflate(R.layout.fragment_alert_2, container, false)
        backlmg =Alert.findViewById(R.id.alarm_background)
        recyclerViewalert =Alert.findViewById(R.id.alarm_recycler)
        addatert =Alert.findViewById(R.id.btn_add_alarm)
        setUpView()
        alertViewModel.getAllWeatherAlarm().observe(viewLifecycleOwner,
            Observer {
                it.let {

                    if (it.isEmpty()){
                        backlmg.visibility=View.VISIBLE

                    }
                    else{
                        backlmg.visibility=View.GONE


                    }
//                    alertAdapter.setDataToAlarmAdapter(it)
                    alertAdapter.notifyDataSetChanged()

                }


            })

        return Alert
    }
    private fun setUpView() {
        alertAdapter= AlertAdapter(arrayListOf(),this)
        recyclerViewalert.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = alertAdapter
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        addatert.setOnClickListener {
            addatert.setOnClickListener{
                Navigation.findNavController(it).navigate(R.id.action_alarmFragment_to_setAlarmsFragment);

            }
        }

    }

    override fun onDeleteAlarmClicked(view: View,id:Int) {
        var popup = PopupMenu(requireContext(),view);
        popup.inflate(R.menu.alert_menu);
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.delete -> {
                   // alertAdapter.deleteALarm(id)
                    alertAdapter.notifyDataSetChanged()

                }

            }
            alertAdapter.notifyDataSetChanged()
            false
        }
        popup.show();

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Alert_Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Alerts_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}