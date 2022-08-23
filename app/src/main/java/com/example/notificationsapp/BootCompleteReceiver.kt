package com.example.notificationsapp


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.impl.utils.ForceStopRunnable

@SuppressLint("RestrictedApi")
class BootCompleteReceiver : ForceStopRunnable.BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if ((intent?.action != null || intent?.action.equals("android.intent.action.BOOT_COMPLETED"))
                && MainRepository.getIsShowNotification(context)
        ) {
            Log.d("MyApp", "onReceive: ${intent?.action}")
            NotificationWorker.scheduleTheNotification(context.applicationContext)
        }
    }
}