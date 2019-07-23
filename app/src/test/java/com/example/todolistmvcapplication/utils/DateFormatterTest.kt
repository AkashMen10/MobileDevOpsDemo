package com.example.todolistmvcapplication.utils

import android.text.TextUtils
import com.example.todolistmvcapplication.utils.DateFormatter
import junit.framework.Assert.*
import org.junit.Assert.assertNotEquals
import org.junit.Test
import java.util.*

class DateFormatterTest {

    //*************************************************************************************************************************
    //*************************************************************************************************************************
    //******************************* TEST CASES FOR getFormattedDate()********************************************************
    //*************************************************************************************************************************
    //*************************************************************************************************************************

    //Test for Correct Value
    @Test
    fun getFormattedDateValidValueTest() {
        assertEquals(
            DateFormatter.getFormattedDate(
                DateFormatter.dd_MM_yyyy,
                "20/08/2017", DateFormatter.dd_MM_yyyy_HH_mm
            ), "20-08-2017 00:00"
        )
    }


    //Test for any Empty Value
    @Test
    fun getFormattedDateEmptyValueTest() {
        assertNotEquals(
            DateFormatter.getFormattedDate(
                DateFormatter.dd_MM_yyyy,
                "20/08/2017", DateFormatter.dd_MM_yyyy_HH_mm
            ), ""
        )
    }

    //*************************************************************************************************************************
    //*************************************************************************************************************************
    //******************************* TEST CASES FOR getStringFromDate()********************************************************
    //*************************************************************************************************************************
    //*************************************************************************************************************************

    //Test for correct value of Today's date
    @Test
    fun getStringFromDateValidValueTest() {
        assertEquals(DateFormatter.getStringFromDate(Date(), DateFormatter.dd_MM_yyyy), "11/07/2019")
    }

    //Test for non-empty value of Today's date
//    @Test
//    fun getStringFromDateEmptyValueTest() {
//        assertFalse(TextUtils.equals(DateFormatter.getStringFromDate(Date(), DateFormatter.dd_MM_yyyy), ""))
//    }

    //*************************************************************************************************************************
    //*************************************************************************************************************************
    //******************************* TEST CASES FOR getDateFromString()********************************************************
    //*************************************************************************************************************************
    //*************************************************************************************************************************

    //Test for non-null value of Generated Date Object
    @Test
    fun getDateFromStringNotNullTest() {
        assertNotNull(DateFormatter.getDateFromString(DateFormatter.dd_MM_yyyy, "23/05/2019"))
    }

    //Test for correct value of Generated Date Object
    @Test
    fun getDateFromStringCorrectValueTest() {
        val todayDate = DateFormatter.getDateFromString(DateFormatter.dd_MM_yyyy, "23/05/2019")
        val strTodayDate = DateFormatter.getStringFromDate(todayDate, DateFormatter.dd_MM_yyyy)
        assertEquals(strTodayDate, "23/05/2019")
    }


}