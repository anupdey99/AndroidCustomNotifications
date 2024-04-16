package com.anupdey.androidcustomnotifications.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import androidx.core.app.NotificationManagerCompat
import com.anupdey.androidcustomnotifications.R
import com.anupdey.androidcustomnotifications.data.NotificationData
import com.anupdey.androidcustomnotifications.util.list.ListService
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object NotificationManager {

    fun process(context: Context, notificationData: NotificationData) {
        createNotificationChannel(context = context)
        when (notificationData.type) {
            NotificationType.DEFAULT -> showDefaultNotification(context, notificationData)
            NotificationType.BANNER -> showBannerNotification(context, notificationData)
            NotificationType.ACTION -> showActionNotification(context, notificationData)
            NotificationType.CAROUSEL -> showCarouselNotification(context, notificationData)
            NotificationType.TIMER -> showTimerNotification(context, notificationData)
        }
    }

    private fun createNotificationChannel(context: Context) {
        val channelId = context.getString(R.string.notification_channel_default)
        createNotificationChannel(context, channelId)
    }

    fun dismissNotification(context: Context, notificationId: Int) {
        NotificationManagerCompat.from(context).cancel(notificationId)
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

    private fun showCarouselNotification(context: Context, notificationData: NotificationData) {

        val remoteViews = RemoteViews(context.packageName, R.layout.layout_notification_carousel)
        remoteViews.setTextViewText(R.id.title, notificationData.title)
        remoteViews.setTextViewText(R.id.content, notificationData.content)
        remoteViews.setViewVisibility(R.id.viewFlipper, View.VISIBLE)
        remoteViews.setInt(R.id.viewFlipper, "setFlipInterval", notificationData.carouselIntervalInMillis)


        //init notification
        notificationData.remoteViews = remoteViews
        val notificationBuilder = createNotification(context, notificationData)
        showNotification(context, notificationData.notificationId, notificationBuilder.build())

        CoroutineScope(Dispatchers.IO).launch {
            remoteViews.removeAllViews(R.id.viewFlipper)

            notificationData.carousel?.forEach {
                try {
                    val futureBitmap = Glide.with(context)
                        .asBitmap()
                        .load(it.image)
                        .submit(300,150)
                    val bitmap = futureBitmap.get()

                    if (bitmap != null) {
                        val viewFlipperImage = RemoteViews(context.packageName, R.layout.view_notification_image)
                        viewFlipperImage.setImageViewBitmap(R.id.imageView, bitmap)
                        remoteViews.addView(R.id.viewFlipper, viewFlipperImage)
                        Log.d("debugNotification", "image downloaded")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            //update notification
            notificationData.remoteViews = remoteViews
            val notificationBuilder = createNotification(context, notificationData)
            showNotification(context, notificationData.notificationId, notificationBuilder.build())
        }

    }

    private fun showTimerNotification(context: Context, notificationData: NotificationData) {
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

        /*Handler(Looper.getMainLooper()).postDelayed({
            dismissNotification(context, notificationData.notificationId)
        }, notificationData.countDownTimer)*/
    }

}