package com.example.todolistmvcapplication.activities

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.todolistmvcapplication.R
import com.example.todolistmvcapplication.models.Event
import com.example.todolistmvcapplication.utils.DateFormatter
import com.example.todolistmvcapplication.utils.Utils
import java.text.ParseException
import java.util.*

class EditEventActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    companion object {
        const val REQ_EDIT_EVENT = 35
        const val EVENT_OBJ = "event_object"

        fun beginActivity(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, EditEventActivity::class.java))
        }

        fun beginActivityForResult(
            activity: AppCompatActivity,
            event: Event
        ) {
            val intent = Intent(activity, EditEventActivity::class.java)
            intent.putExtra(EVENT_OBJ, event)
            activity.startActivityForResult(intent, REQ_EDIT_EVENT)
        }
    }

    private var mSelectedDate: Date? = null
    private lateinit var mEvent: Event

    private lateinit var edtEventName: EditText
    private lateinit var txtEventTime: TextView
    private lateinit var btnSelectTime: Button
    private lateinit var edtEventDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)
        actionBar?.title = getString(R.string.edit_event)
        mEvent = intent.getSerializableExtra(EVENT_OBJ) as Event
        initViews()
        setViews()
    }

    private fun initViews() {
        edtEventName = findViewById(R.id.edt_event_name)
        txtEventTime = findViewById(R.id.txt_event_time)
        btnSelectTime = findViewById(R.id.btn_select_time)
        edtEventDescription = findViewById(R.id.edt_event_description)
    }

    private fun setViews() {
        btnSelectTime.setOnClickListener {
            showDatePicker()
        }
        if (mEvent.eventName!!.isNotEmpty()) {
            edtEventName.setText(mEvent.eventName)
        }
        if (mEvent.eventDescription!!.isNotEmpty()) {
            edtEventDescription.setText(mEvent.eventDescription)
        }
        if (mEvent.eventTime != null) {
            mSelectedDate = mEvent.eventTime
            txtEventTime.text = DateFormatter.getStringFromDate(mSelectedDate!!, DateFormatter.dd_MM_yyyy_HH_mm)
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        val dialog = DatePickerDialog(
            this, this,
            calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        dialog.setTitle("Select Date")
        dialog.show()
    }

    //After setting date, immediately the 'TimPickerDialogue' is shown to select time.
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.YEAR, year)
        mSelectedDate = calendar.time
        showTimePicker()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        val mTimePicker: TimePickerDialog
        mTimePicker = TimePickerDialog(
            this, this, calendar.get(Calendar.HOUR_OF_DAY)
            , calendar.get(Calendar.MINUTE), true
        )
        mTimePicker.setTitle("Select Time")
        mTimePicker.show()
    }

    //The time selected by User is set to the 'Date' object where the date was set previously
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.time = mSelectedDate
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        mSelectedDate = calendar.time

        txtEventTime.text = DateFormatter.getStringFromDate(mSelectedDate!!, DateFormatter.dd_MM_yyyy_HH_mm)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_event, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //Event Model is filled with data and returned to previous screen -> 'MainActivity'
    private fun prepareEventModel(): Event? {
        //Check for empty event name
        if (edtEventName.text.toString().trim().isEmpty()) {
            Utils.showToast(getString(R.string.plz_enter_event_name))
            return null
        }

        //Check for proper event time format
        try {
            DateFormatter.getDateFromString(DateFormatter.dd_MM_yyyy_HH_mm, txtEventTime.text.toString().trim())
        } catch (ex: ParseException) {
            Utils.showToast(getString(R.string.plz_select_proper_event_time))
            return null
        }

        //Check for empty event description
        if (edtEventDescription.text.toString().trim().isEmpty()) {
            Utils.showToast(getString(R.string.plz_enter_event_description))
            return null
        }

        //Prepare and return event object
        mEvent.eventName = edtEventName.text.toString().trim()
        mEvent.eventTime = DateFormatter.getDateFromString(
            DateFormatter.dd_MM_yyyy_HH_mm
            , txtEventTime.text.toString()
        )
        mEvent.eventDescription = edtEventDescription.text.toString().trim()
        return mEvent
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_done) {
            val event = prepareEventModel()
            if (event != null) {
                intent.putExtra(EVENT_OBJ, event)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
