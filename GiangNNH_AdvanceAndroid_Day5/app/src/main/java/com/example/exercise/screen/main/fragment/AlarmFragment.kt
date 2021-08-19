package com.example.exercise.screen.main.fragment

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.exercise.R
import com.example.exercise.broadcast.AlarmReceiver
import com.example.exercise.databinding.FragmentAlarmBinding
import com.example.exercise.screen.main.MainActivity
import com.example.exercise.service.MusicService
import com.example.exercise.utils.Constants
import java.util.*

class AlarmFragment : Fragment() {

    private lateinit var fragmentAlarmBinding: FragmentAlarmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentAlarmBinding = FragmentAlarmBinding.inflate(inflater)
        onClickBookALarm()
        return fragmentAlarmBinding.root
    }

    private fun onClickBookALarm() {
        fragmentAlarmBinding.buttonStartALarm.setOnClickListener {
            setTimeALarm()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setTimeALarm() {
        val hour = fragmentAlarmBinding.timePicker.hour
        val minute = fragmentAlarmBinding.timePicker.minute
        val startTime = Calendar.getInstance().apply {
            set(Calendar.HOUR, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }
        val alarmStartTime = startTime.timeInMillis


        val intent = Intent(context, AlarmReceiver::class.java).apply {
            action = Constants.ACTION_SET_ALARM
        }

        val alarmPendingIntent =
            PendingIntent.getBroadcast(context, 0, intent, 0)

        (activity as MainActivity).alarm.set(
            AlarmManager.RTC_WAKEUP,
            alarmStartTime,
            alarmPendingIntent
        )
        fragmentAlarmBinding.time = "$hour : $minute"
        Toast.makeText(context, getString(R.string.toast_message_book_alarm), Toast.LENGTH_LONG)
            .show()
    }
}