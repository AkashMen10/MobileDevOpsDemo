package com.example.todolistmvcapplication.utils

import com.example.todolistmvcapplication.models.Event
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object Prefs {

    val EVENT_LIST = "event_list"

    var eventList: ArrayList<Event>?
        get() {
            val gson = Gson()
            val json = gson.toJson(MyApplication.getInstance().prefs?.getString(EVENT_LIST, null))
            val type = object : TypeToken<ArrayList<Event>?>() {}.type
            return if ((gson.fromJson(json, type) as ArrayList<Event>?).isNullOrEmpty()) {
                ArrayList()
            } else
                gson.fromJson(json, type) as ArrayList<Event>?
        }
        set(value) {
            val gson = Gson()
            val json = gson.toJson(value)
            MyApplication.getInstance().prefs?.edit()?.putString(EVENT_LIST, json)?.apply()
        }

}