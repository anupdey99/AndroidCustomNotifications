package com.anupdey.androidcustomnotifications.data

data class NotificationData(
    val title: String = "",
    val content: String = "",
    val ticker: String = "",

    val channelId: String = "",
    val notificationId: Int = 123
)
