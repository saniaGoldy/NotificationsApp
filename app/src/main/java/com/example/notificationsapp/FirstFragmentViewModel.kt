package com.example.notificationsapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel

class FirstFragmentViewModel(application: Application): AndroidViewModel(application) {
    fun setIsShowNotification(value: Boolean) {

        val sharedPreferences = getApplication<Application>().getSharedPreferences(SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )

        sharedPreferences.edit().putBoolean(IS_SHOW_NOTIFICATION_PREFERENCE_NAME, value).apply()
    }
    companion object{

        const val IS_SHOW_NOTIFICATION_PREFERENCE_NAME = "isShowNotification"
        const val SHARED_PREFERENCES_NAME = "mySharedPref"

        fun getIsShowNotification(context: Context): Boolean {
            return context.getSharedPreferences(
                SHARED_PREFERENCES_NAME,
                Context.MODE_PRIVATE
            ).getBoolean(
                IS_SHOW_NOTIFICATION_PREFERENCE_NAME, false
            )
        }
    }
}