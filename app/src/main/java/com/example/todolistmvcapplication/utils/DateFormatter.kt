package com.example.todolistmvcapplication.utils

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

object DateFormatter {

    val dd_MM_yyyy = "dd/MM/yyyy"
    val dd_MM_yyyy_HH_mm = "dd-MM-yyyy HH:mm"
    val MMM_dd_yyyy = "MMM dd, yyyy"

    fun getFormattedDate(inFormat: String, strDate: String, outFormat: String): String {
        val fmt = SimpleDateFormat(inFormat, Locale.getDefault())
        val date = fmt.parse(strDate)
        val fmtOut = SimpleDateFormat(outFormat, Locale.getDefault())
        return fmtOut.format(date)
    }

    fun getStringFromDate(date: Date, outFormat: String): String {
        val fmtOut = SimpleDateFormat(outFormat, Locale.getDefault())
        return fmtOut.format(date)
    }

    fun getDateFromString(inFormat: String, strDate: String): Date {
        val fmt = SimpleDateFormat(inFormat, Locale.getDefault())
        return fmt.parse(strDate)
    }

}