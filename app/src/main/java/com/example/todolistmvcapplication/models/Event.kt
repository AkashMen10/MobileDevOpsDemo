package com.example.todolistmvcapplication.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.databinding.BaseObservable
import com.example.todolistmvcapplication.utils.TimeStampConverter
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true) @SerializedName("id") var id: Int,
    @ColumnInfo(name = "event_name") @SerializedName("eventName") var eventName: String? = "XXXXX",
    @ColumnInfo(name = "event_time") @SerializedName("eventTime") var eventTime: Date? = null,
    @ColumnInfo(name = "event_description") @SerializedName("eventDescription") var eventDescription: String? = "zzzzzzzzzzzzzzzzzzzzzzzzz"
) : Serializable, BaseObservable()