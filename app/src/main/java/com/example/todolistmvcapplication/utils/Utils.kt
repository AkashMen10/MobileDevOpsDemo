package com.example.todolistmvcapplication.utils

import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast

object Utils {
    fun showToast(msg: String) {
        Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT).show()
    }

    fun isEmailValid(email: String): Boolean {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}