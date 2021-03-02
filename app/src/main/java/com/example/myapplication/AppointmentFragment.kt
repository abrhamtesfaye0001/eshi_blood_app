package com.example.myapplication

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment

import com.example.myapplication.databinding.FragmentAppointmentBinding
import java.text.SimpleDateFormat
import java.util.*


class AppointmentFragment : Fragment() {
    private lateinit var binding: FragmentAppointmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAppointmentBinding.inflate(layoutInflater)
        return binding.root

//        return inflater.inflate(R.layout.fragment_appointment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // binding.id
        val startDatePickerButton = binding.startDateButton
        val endDatePickerButton = binding.endDateButton
        binding.startDate.text = getTodaysDate()
        binding.endDate.text = getTodaysDate()
        var datePickerDialog:DatePickerDialog

        startDatePickerButton.setOnClickListener {
            Log.d("clicked","start")
            openDatePicker(initDatePicker(true))

        }
        endDatePickerButton.setOnClickListener {
            Log.d("clicked","end")
            openDatePicker(initDatePicker(false))


        }
        binding.startHour.setOnClickListener {
            getTime(binding.startHour as TextView,activity as MainActivity)

        }
        binding.endHour.setOnClickListener {
            getTime(binding.endHour as TextView,activity as MainActivity)

        }


    }
    fun getTime(textView: TextView, context: Context){

        val cal = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)

            textView.text = SimpleDateFormat("HH:mm").format(cal.time)
        }

        textView.setOnClickListener {
            TimePickerDialog(context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
    }
    private fun getTodaysDate(): String {
        var cal:Calendar = Calendar.getInstance()
        var year:Int = cal.get(Calendar.YEAR)
        var month:Int = cal.get(Calendar.MONTH)
        month+=1
        var day:Int = cal.get(Calendar.DAY_OF_MONTH)
        return  getMonthFormat(month)+" "+day+" "+year

    }

    fun initDatePicker(isStart:Boolean):DatePickerDialog{
//        var view:DatePicker?
//        var year:Int
//        var month:Int
//        var dayOfMonth:Int

        var  dateSetListener:DatePickerDialog.OnDateSetListener= DatePickerDialog.OnDateSetListener() { datePicker: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
//            fun OnDateSet(datePicker: DatePicker, year: Int, month: Int, dayOfMonth: Int){
            var monthVar = month + 1
            var date: String = getMonthFormat(monthVar) + " " + dayOfMonth + " " + year
            Log.d("booooooooooooooooool",isStart.toString())
            when (isStart) {
                true -> binding.startDate.text = date
                false -> binding.endDate.text = date
            }
        }
//            if(isStart===true){ binding.startDate.text = date }
//
//                Log.d("ccccccccccccccccccccccc","clicked")}
//            }
        var cal:Calendar = Calendar.getInstance()
        var year:Int = cal.get(Calendar.YEAR)
        var month:Int = cal.get(Calendar.MONTH)
        var day:Int = cal.get(Calendar.DAY_OF_MONTH)
        var datePickerDialog:DatePickerDialog = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog(activity as MainActivity,dateSetListener,year,month,day )
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        return datePickerDialog
    }
    fun openDatePicker(datePickerDialog:DatePickerDialog){
        datePickerDialog.show()
    }
    fun getMonthFormat(month:Int):String{
        var monthStr = "month"
        when(month){
            9->monthStr = "SEP"
            10->monthStr =  "OCT"
            11->monthStr =  "NOV"
            12->monthStr = "DEC"
            1->monthStr =  "JAN"
            2->monthStr =  "FEB"
            3->monthStr =   "MAR"
            4->monthStr =   "APR"
            5->monthStr =   "MAY"
            6->monthStr =   "JUN"
            7->monthStr =   "JUL"
            8->monthStr =   "AUG"
        }
        return monthStr
    }
}

