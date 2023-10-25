
package com.example.pushnotificationtraining

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    val CHANNEL_ID = "GFG"

    lateinit var et1: EditText
    lateinit var et2: EditText
    lateinit var btn1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Binding Views by their IDs
        et1 = findViewById(R.id.et1)
        et2 = findViewById(R.id.et2)
        btn1 = findViewById(R.id.btn1)
        btn1.setOnClickListener {

            // Converting the .png Image file to a Bitmap!
            val imgBitmap = BitmapFactory.decodeResource(resources, R.drawable.notification)

            // Building the notification
            val nBuilder = NotificationCompat.Builder(this, CHANNEL_ID)

                // adding notification Title
                .setContentTitle(et1.text.toString())

                // adding notification Text
                .setContentText(et2.text.toString())

                // adding notification SmallIcon
                .setSmallIcon(R.drawable.icon_notifications)

                // adding notification Priority
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                // making the notification clickable
                .setAutoCancel(true)

                // adding largeIcon
                .setLargeIcon(imgBitmap)

                // making notification Expandable
                .setStyle(NotificationCompat.BigPictureStyle()
                    .bigPicture(imgBitmap)
                    .bigLargeIcon(null))
                .build()

            // finally notifying the notification
            val nManager = NotificationManagerCompat.from(this)
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return@setOnClickListener
            }
            nManager.notify(1, nBuilder)
        }
    }
}
