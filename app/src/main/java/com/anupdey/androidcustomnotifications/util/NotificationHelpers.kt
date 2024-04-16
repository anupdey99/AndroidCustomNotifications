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
    val builder = NotificationCompat.Builder(context, notification.channelId)
        .setContentTitle(notification.title)
        .setContentText(notification.content)
        .setTicker(notification.ticker)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentIntent(pendingIntent)
        .setPriority(getNotificationPriority(notification.priority))
        .setAutoCancel(true)

    if (notification.bitmap != null) {
        //builder.setLargeIcon(notification.bitmap)
        builder.setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(notification.bitmap)
        )
    }

    if (notification.type == NotificationType.ACTION) {
        if (!notification.action1Name.isNullOrEmpty() && notification.action1PendingIntent != null) {
            builder.addAction(
                notification.actionId,
                notification.action1Name,
                notification.action1PendingIntent
            )
        }
        if (!notification.action2Name.isNullOrEmpty() && notification.action2PendingIntent != null) {
            builder.addAction(
                notification.actionId,
                notification.action2Name,
                notification.action2PendingIntent
            )
        }
    }

    if (notification.type == NotificationType.CAROUSEL) {
        builder.setOnlyAlertOnce(true)
        builder.setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomBigContentView(notification.remoteViews)
            .setCustomHeadsUpContentView(notification.remoteViews)
            //.setCustomContentView(notification.remoteViews)
    }

    if (notification.type == NotificationType.TIMER) {
        builder.setOnlyAlertOnce(true)
        builder.setChronometerCountDown(true)
        builder.setUsesChronometer(true)
        builder.setShowWhen(true)
        builder.setWhen(notification.countDownTimer + System.currentTimeMillis())
        builder.setTimeoutAfter(notification.countDownTimer)
        builder.setDeleteIntent(notification.action1PendingIntent)
    }

    return builder
}

private fun getNotificationPriority(priority: Int): Int {
    return if (priority == 0)
        NotificationCompat.PRIORITY_DEFAULT
    else
        NotificationCompat.PRIORITY_MAX
}

