package com.example.todolistmvcapplication.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.provider.ContactsContract.CommonDataKinds.Note
import com.example.todolistmvcapplication.models.Event
import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update


@Dao
interface EventDao {
    @Insert
    fun insertEvent(event: Event): Long?

    @Update
    fun updateEvent(event: Event): Int

    @Query("SELECT * FROM events")
    fun getEvents(): List<Event>

    @Query("SELECT * FROM events WHERE id=1")
    fun getFirstEvent(): Event

}