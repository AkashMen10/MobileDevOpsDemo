package com.example.todolistmvcapplication.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.BaseObservable
import android.databinding.ViewDataBinding
import android.os.Handler
import android.view.View
import com.example.todolistmvcapplication.models.User

class OnboardViewModel() : ViewModel() {


    var user = User()

    init {

        Handler().postDelayed({
            user.email = "email@view.model"
            user.password = "123456"
        }, 2000)


    }


}