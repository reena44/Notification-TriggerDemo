package com.example.notificationtrigger

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val notification: String = "Notification message here"
    private val id: String = "channel id"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {

            triggerNotification()
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun triggerNotification() {


        val builder = NotificationCompat.Builder(this, id)
                .setSmallIcon(R.drawable.ic_launcher_background)
                builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher_background))
                .setContentTitle(notification)
                .setContentText(notification)
                .setStyle(NotificationCompat.BigTextStyle().bigText("Intent"))

                .setAutoCancel(true)

        val intent = Intent(this, Main2Activity::class.java)
        intent.putExtra("meassge",notification)

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK and  Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        builder. setContentIntent(pendingIntent)


        val notificationManagerCompat  = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManagerCompat.notify(10,builder.build())

    }
}

