package com.anupdey.androidcustomnotifications.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.anupdey.androidcustomnotifications.R
import com.anupdey.androidcustomnotifications.data.NotificationData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

object NotificationManager {

    fun process(context: Context, notificationData: NotificationData) {
        createNotificationChannel(context = context)
        when (notificationData.type) {
            NotificationType.DEFAULT -> showDefaultNotification(context, notificationData)
            NotificationType.BANNER -> showBannerNotification(context, notificationData)
            NotificationType.ACTION -> showActionNotification(context, notificationData)
        }
    }

    private fun showDefaultNotification(context: Context, notificationData: NotificationData) {
        val notificationBuilder = createNotification(context, notificationData)
        showNotification(context, notificationData.notificationId, notificationBuilder.build())
    }

    private fun showBannerNotification(context: Context, notificationData: NotificationData) {
        Glide.with(context)
            .asBitmap()
            .load(notificationData.url)
            .into(object : CustomTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    notificationData.bitmap = resource
                    val notificationBuilder = createNotification(context, notificationData)
                    showNotification(context, notificationData.notificationId, notificationBuilder.build())
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    val notificationBuilder = createNotification(context, notificationData)
                    showNotification(context, notificationData.notificationId, notificationBuilder.build())
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })

    }

    private fun showActionNotification(context: Context, notificationData: NotificationData) {
        Glide.with(context)
            .asBitmap()
            .load(notificationData.url)
            .into(object : CustomTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    notificationData.bitmap = resource
                    val notificationBuilder = createNotification(context, notificationData)
                    showNotification(context, notificationData.notificationId, notificationBuilder.build())
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    val notificationBuilder = createNotification(context, notificationData)
                    showNotification(context, notificationData.notificationId, notificationBuilder.build())
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }

    private fun createNotificationChannel(context: Context) {
        val channelId = context.getString(R.string.notification_channel_default)
        createNotificationChannel(context, channelId)
    }
}