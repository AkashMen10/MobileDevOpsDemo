package com.example.todolistmvcapplication.models

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.os.Parcel
import android.os.Parcelable

data class User(
    var username: String?,
    var password: String?,
    var firstName: String?,
    var lastName: String?,
    var email: String?,
    var userId: String?
) : Parcelable, BaseObservable() {
    constructor(parcel: Parcel?) : this(
        parcel?.readString(),
        parcel?.readString(),
        parcel?.readString(),
        parcel?.readString(),
        parcel?.readString(),
        parcel?.readString()
    ) {
    }

    constructor() : this(null)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(email)
        parcel.writeString(userId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}