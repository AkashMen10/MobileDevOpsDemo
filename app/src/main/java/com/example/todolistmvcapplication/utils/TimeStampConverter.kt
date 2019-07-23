package com.example.todolistmvcapplication.utils

import android.arch.persistence.room.TypeConverter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TimeStampConverter {
    private val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: String?): Date? {
        if (value != null) {
            try {
                val timeZone = TimeZone.getTimeZone("IST")
                df.timeZone = timeZone
                return df.parse(value)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return null
        } else {
            return null
        }
    }


    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(value: Date?): String? {
        val timeZone = TimeZone.getTimeZone("IST")
        df.timeZone = timeZone
        return if (value == null) null else df.format(value)
    }
}