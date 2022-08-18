package com.example.notificationsapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.notificationsapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

const val IS_SHOW_NOTIFICATION_PREFERENCE_NAME = "isShowNotification"
const val SHARED_PREFERENCES_NAME = "mySharedPref"

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        createNotificationChannel()
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        with(binding){
            startButton.setOnClickListener {
                setIsShowNotification(true)
                NotificationWorker.scheduleTheNotification(requireContext().applicationContext)
            }
            stopButton.setOnClickListener {
                setIsShowNotification(false)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setIsShowNotification(value: Boolean) {

        val sharedPreferences = requireContext().getSharedPreferences(SHARED_PREFERENCES_NAME,
            MODE_PRIVATE
        )

        sharedPreferences.edit().putBoolean(IS_SHOW_NOTIFICATION_PREFERENCE_NAME, value).apply()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val repeatInterval: Long = 30
        const val CHANNEL_ID = "666"
        const val NOTIFICATION_ID = 777
    }
}