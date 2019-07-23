package com.example.todolistmvcapplication.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import com.example.todolistmvcapplication.models.Event
import java.util.*

class DateTimePickerHandler : DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {


    private var mEvent: Event? = null


    fun onClicked(view: View, event: Event) {
        mEvent = event
        //Show DatePicker
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        val dialog = DatePickerDialog(
            view.context, this,
            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        dialog.setTitle("Select Date")
        dialog.show()
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)
        mEvent?.eventTime = calendar.time

        //Show TimePicker
        val mTimePicker: TimePickerDialog
        mTimePicker = TimePickerDialog(
            view?.context, this, calendar.get(Calendar.HOUR_OF_DAY)
            , calendar.get(Calendar.MINUTE), true
        )
        mTimePicker.setTitle("Select Time")
        mTimePicker.show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.time = mEvent?.eventTime
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        mEvent?.eventTime = calendar.time
    }

}