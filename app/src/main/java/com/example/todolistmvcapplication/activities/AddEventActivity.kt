package com.example.todolistmvcapplication.activities

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.todolistmvcapplication.R
import com.example.todolistmvcapplication.databinding.ActivityAddEventBinding
import com.example.todolistmvcapplication.models.Event
import com.example.todolistmvcapplication.utils.DateTimePickerHandler
import com.example.todolistmvcapplication.utils.Utils
import java.util.*


class AddEventActivity : AppCompatActivity() {

    companion object {
        const val REQ_ADD_EVENT = 34
        const val EVENT_OBJ = "event_object"

        fun beginActivity(activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, AddEventActivity::class.java))
        }

        fun beginActivityForResult(activity: AppCompatActivity) {
            activity.startActivityForResult(Intent(activity, AddEventActivity::class.java), REQ_ADD_EVENT)
        }
    }

    private var mEvent = Event(0, "", Date(), "")

    private lateinit var edtEventName: EditText
    private lateinit var txtEventTime: TextView
    private lateinit var btnSelectTime: Button
    private lateinit var edtEventDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handler = DateTimePickerHandler()

        val activityBinding = DataBindingUtil.setContentView<ActivityAddEventBinding>(this, R.layout.activity_add_event)
        activityBinding.event = mEvent
        activityBinding.handler = handler

        actionBar?.title = getString(R.string.add_event)
        initViews()
    }

    private fun initViews() {
        edtEventName = findViewById(R.id.edt_event_name)
        txtEventTime = findViewById(R.id.txt_event_time)
        btnSelectTime = findViewById(R.id.btn_select_time)
        edtEventDescription = findViewById(R.id.edt_event_description)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_event, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun prepareEventModel(): Boolean {
        //Check for empty event name
        if (mEvent.eventName.isNullOrEmpty()) {
            Utils.showToast(getString(R.string.plz_enter_event_name))
            return false
        }

        //Check for proper event time format
        if (mEvent.eventTime == null) {
            Utils.showToast(getString(R.string.plz_select_proper_event_time))
            return false
        }

        //Check for empty event description
        if (mEvent.eventDescription.isNullOrEmpty()) {
            Utils.showToast(getString(R.string.plz_enter_event_description))
            return false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_done) {
            if (prepareEventModel()) {
                intent.putExtra(EVENT_OBJ, mEvent)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
