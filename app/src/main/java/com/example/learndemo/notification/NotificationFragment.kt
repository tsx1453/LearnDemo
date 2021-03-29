package com.example.learndemo.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.learndemo.R
import com.example.learndemo.base.BaseFragment

/**
 * Description:
 * Author: TianShuxin
 * Date: 2021/1/13
 */
class NotificationFragment : BaseFragment(), View.OnClickListener {
    private val notificationChannel: String = "channel"
    private val notificationManagerCompat: NotificationManagerCompat by lazy {
        NotificationManagerCompat.from(requireContext())
    }

    override fun layoutId(): Int = R.layout.fragment_notification;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_normal_notify).setOnClickListener(this)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationManagerCompat.createNotificationChannel(
                    NotificationChannel(notificationChannel, "channel name", NotificationManager.IMPORTANCE_MIN))
        }
    }

    override fun onClick(v: View?) {
        val builder = NotificationCompat.Builder(requireContext(), notificationChannel)
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Content Title")
                .setContentText("Content Text")
        notificationManagerCompat.notify(1, builder.build())
    }
}