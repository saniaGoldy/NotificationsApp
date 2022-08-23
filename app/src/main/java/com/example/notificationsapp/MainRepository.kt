package com.example.notificationsapp

import android.content.Context

object MainRepository {
    const val IS_SHOW_NOTIFICATION_PREFERENCE_NAME = "isShowNotification"
    const val SHARED_PREFERENCES_NAME = "mySharedPref"

    fun setIsShowNotification(value: Boolean, context: Context) {

        val sharedPreferences = context.getSharedPreferences(
            SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )

        sharedPreferences.edit()
            .putBoolean(IS_SHOW_NOTIFICATION_PREFERENCE_NAME, value).apply()
    }

    fun getIsShowNotification(context: Context): Boolean {
        return context.getSharedPreferences(
            SHARED_PREFERENCES_NAME,
            Context.MODE_PRIVATE
        ).getBoolean(
            IS_SHOW_NOTIFICATION_PREFERENCE_NAME, false
        )
    }
}
