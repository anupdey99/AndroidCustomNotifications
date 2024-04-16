package com.anupdey.androidcustomnotifications.data

import android.app.PendingIntent
import android.graphics.Bitmap
import android.widget.RemoteViews
import com.anupdey.androidcustomnotifications.util.NotificationType

data class NotificationData(
    val type: NotificationType = NotificationType.DEFAULT,
    val title: String = "",
    val content: String = "",
    val ticker: String = "",
    val url: String = "",

    val actionId: Int = 0,
    val action1Name: String? = "",
    val action1PendingIntent: PendingIntent? = null,
    val action2Name: String? = "",
    val action2PendingIntent: PendingIntent? = null,

    var carousel: List<CarouselData>? = null,
    var carouselIntervalInMillis: Int = 3000,

    var countDownTimer: Long = 0,

    val channelId: String = "",
    val notificationId: Int = 123,
    var priority: Int = 1, //0 default, 1 high
    var bitmap: Bitmap? = null,
    var remoteViews: RemoteViews? = null
)
