package com.anupdey.androidcustomnotifications.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.anupdey.androidcustomnotifications.MainActivity
import com.anupdey.androidcustomnotifications.R
import com.anupdey.androidcustomnotifications.data.NotificationData

fun createNotificationChannel(context: Context, channelId: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val serviceChannel = NotificationChannel(
            channelId, "Default Channel",
            NotificationManager.IMPORTANCE_HIGH
        )
        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(serviceChannel)
    }
}

fun showNotification(context: Context, id: Int, notification: Notification) {
    val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    manager.notify(id, notification)
}

fun createNotification(context: Context, notification: NotificationData): NotificationCompat.Builder {
    val intent = Intent(context, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    return NotificationCompat.Builder(context, notification.channelId)
        .setContentTitle(notification.title)
        .setContentText(notification.content)
        .setTicker(notification.ticker)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentIntent(pendingIntent)
}

fun createNotification(context: Context, remoteViews: RemoteViews, channelId: String): Notification {
    val intent = Intent(context, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    return NotificationCompat.Builder(context, channelId)
        .setContentTitle("OTP Reader")
        .setContentText("Running on background")
        .setTicker("OTP Reader")
        .setOngoing(false)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentIntent(pendingIntent)
        .setStyle(NotificationCompat.DecoratedCustomViewStyle())
        .setCustomBigContentView(remoteViews)
        .setCustomHeadsUpContentView(remoteViews)
        .setCustomContentView(remoteViews)
        .build()
}

