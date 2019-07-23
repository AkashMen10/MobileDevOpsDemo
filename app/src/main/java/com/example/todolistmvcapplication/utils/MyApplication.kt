package com.example.todolistmvcapplication.utils

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.SharedPreferences
import com.example.todolistmvcapplication.database.EventDatabase

class MyApplication : Application() {

    var prefs: SharedPreferences? = null
    var eventDB: EventDatabase? = null

    companion object {
        private var appInstance: MyApplication? = null
        var viewModelFactory: ViewModelProvider.AndroidViewModelFactory? = null
        fun getInstance(): MyApplication {
            if (appInstance == null) {
                synchronized(MyApplication::class.java) {
                    appInstance = MyApplication()
                }
            }
            return appInstance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        eventDB = EventDatabase.getDatabase(this)
        prefs = getSharedPreferences("TODO_LIST", Context.MODE_PRIVATE)
        viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
    }


}