package com.example.todolistmvcapplication.viewmodels

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.example.todolistmvcapplication.R
import com.example.todolistmvcapplication.activities.MainActivity
import com.example.todolistmvcapplication.models.Event
import com.example.todolistmvcapplication.utils.MyApplication
import com.example.todolistmvcapplication.utils.Utils
import kotlin.collections.ArrayList

class EventViewModel : ViewModel() {

    private var mEventList: MutableLiveData<ArrayList<Event>>? = null

    fun getEvents(): LiveData<ArrayList<Event>> {
        if (mEventList == null) {
            mEventList = MutableLiveData()
            loadEvents()
        }
        return mEventList as MutableLiveData<ArrayList<Event>>
    }

    @SuppressLint("StaticFieldLeak")
    private fun loadEvents() {
        // This task is used to fill the events list at beginning
        object : AsyncTask<Void, Void, List<Event>>() {
            override fun doInBackground(vararg params: Void?): List<Event> {
                return MyApplication.getInstance().eventDB!!.eventDao().getEvents()
            }

            override fun onPostExecute(eventList: List<Event>?) {
                mEventList?.postValue((eventList as ArrayList<Event>?))
            }
        }.execute()
    }

    @SuppressLint("StaticFieldLeak")
    fun insertEvent(event: Event) {
        /**
         * This task is used to add the event in DB
         * Its used immediately after filled 'Event' object is returned by 'AddEventActivity' in 'onActivityResult()'
         */
        object : AsyncTask<Void, Void, Boolean>() {
            override fun doInBackground(vararg params: Void?): Boolean {
                MyApplication.getInstance().eventDB!!.eventDao().insertEvent(event)
                return true
            }

            override fun onPostExecute(bool: Boolean?) {
                if (bool!!) {
//                    Utils.showToast(context.getString(R.string.event_added_to_db))
                }
            }
        }.execute()
    }

    @SuppressLint("StaticFieldLeak")
    fun updateEvent(event: Event) {
        /**
         * This task is used to update the event in DB
         * Its used immediately after filled 'Event' object is returned by 'EditEventActivity' in 'onActivityResult()'
         */
        object : AsyncTask<Void, Void, Boolean>() {
            override fun doInBackground(vararg params: Void?): Boolean {
                MyApplication.getInstance().eventDB!!.eventDao().updateEvent(event)
                return true
            }

            override fun onPostExecute(bool: Boolean?) {
                if (bool!!) {
//                    Utils.showToast(context.getString(R.string.event_updated_in_db))
                }
            }
        }.execute()
    }

}