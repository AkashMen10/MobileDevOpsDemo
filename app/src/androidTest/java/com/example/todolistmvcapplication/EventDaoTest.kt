package com.example.todolistmvcapplication

import com.example.todolistmvcapplication.daos.EventDao
import com.example.todolistmvcapplication.database.EventDatabase
import com.example.todolistmvcapplication.models.Event
import com.example.todolistmvcapplication.utils.MyApplication
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock;
import java.util.*

class EventDaoTest {
    //Non-zero Long return value, to test whether values are inserting

    @Test
    fun insertEventTest() {
        assertNotEquals(


            MyApplication.getInstance().eventDB!!.eventDao().insertEvent(
                Event(
                    0,
                    "TestEvent",
                    Date(),
                    "xyz event is awesome"
                )
            ), 0
        )
    }

    //Non-zero Int return value, to test whether values are updated
    @Test
    fun updateEventTest() {
        assertNotEquals(
            MyApplication.getInstance().eventDB!!.eventDao().updateEvent(
                Event(
                    1,
                    "TestEvent",
                    Date(),
                    "xyz event is awesome"
                )
            ), 0
        )
    }

    //Whether the events in DB not empty
    @Test
    fun getEventsTest() {
        System.out.println("Number of events in DB : "+MyApplication.getInstance().eventDB!!.eventDao().getEvents().size)
        assertNotEquals(
            MyApplication.getInstance().eventDB!!.eventDao().getEvents().size, 0
        )
    }
}