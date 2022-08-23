package com.example.notificationsapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class FirstFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MainRepository

    var isShowNotification = false
        set(value) {
            field = value
            repository.setIsShowNotification(value, getApplication<Application>().applicationContext)
        }

    init {
        isShowNotification = repository.getIsShowNotification(application.applicationContext)
    }

}