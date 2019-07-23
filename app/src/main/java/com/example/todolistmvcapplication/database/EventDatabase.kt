package com.example.todolistmvcapplication.database

import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.room.*
import android.content.Context
import com.example.todolistmvcapplication.daos.EventDao
import com.example.todolistmvcapplication.models.Event
import com.example.todolistmvcapplication.utils.TimeStampConverter

@Database(entities = arrayOf(Event::class), version = 1, exportSchema = false)
@TypeConverters(TimeStampConverter::class)
abstract class EventDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao

    companion object {
        private var INSTANCE: EventDatabase? = null
        fun getDatabase(context: Context): EventDatabase? {
            if (INSTANCE == null) {
                synchronized(EventDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        EventDatabase::class.java, "events.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}