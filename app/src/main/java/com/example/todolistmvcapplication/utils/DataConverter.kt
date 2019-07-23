package com.example.todolistmvcapplication.utils

import android.databinding.BindingAdapter
import android.databinding.InverseMethod
import android.widget.EditText
import android.widget.TextView
import java.util.*

object DataConverter {

    @InverseMethod("stringToDate")
    @JvmStatic
    fun dateToString(
        value: Date
    ): String {
        // Converts Date to String.
        val strDate = DateFormatter.getStringFromDate(value, DateFormatter.dd_MM_yyyy_HH_mm)
        return strDate
    }

    @JvmStatic
    fun stringToDate(
        value: String
    ): Date {
        // Converts String to Date.
        val date = DateFormatter.getDateFromString(DateFormatter.dd_MM_yyyy_HH_mm, value)
        return date
    }
}