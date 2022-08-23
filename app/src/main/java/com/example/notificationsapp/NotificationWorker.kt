package com.example.notificationsapp

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.concurrent.TimeUnit

class NotificationWorker(context: Context, workerParameters: WorkerParameters) :
        Worker(context, workerParameters) {

    private val isShowNotifications: Boolean
        get() = MainRepository.getIsShowNotification(applicationContext)

    override fun doWork(): Result {

        if (isShowNotifications) {
            showNotification()
        }

        return Result.success()
    }


    private fun showNotification() {
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE)
                } else {
                    PendingIntent.getActivity(applicationContext, 0, intent, Intent.FILL_IN_ACTION)
                }

        val builder = NotificationCompat.Builder(applicationContext, FirstFragment.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_sentiment_dissatisfied_24)
                .setContentTitle(applicationContext.getString(R.string.news_title))
                .setContentText(applicationContext.getString(R.string.news_content))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        with(NotificationManagerCompat.from(applicationContext)) {
            notify(FirstFragment.NOTIFICATION_ID, builder.build())
        }
    }

    companion object {
        private const val repeatInterval: Long = 30

        fun scheduleTheNotification(applicationContext: Context) {
            val workManager = WorkManager.getInstance(applicationContext)

            val work = PeriodicWorkRequestBuilder<NotificationWorker>(repeatInterval, TimeUnit.MINUTES).build()
            workManager.enqueue(work)
        }
    }
}
