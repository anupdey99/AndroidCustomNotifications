package com.anupdey.androidcustomnotifications.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import com.anupdey.androidcustomnotifications.MainActivity

class NotificationBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context ?: return
        intent ?: return

        val notificationId = intent.getIntExtra("notificationId", -1)

        val intentActivity = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra("route", intent.getStringExtra("route"))
            putExtra("data", intent.getStringExtra("data"))
        }
        context.startActivity(intentActivity)

        dismissNotification(context, notificationId)
    }

    private fun dismissNotification(context: Context, notificationId: Int) {
        NotificationManagerCompat.from(context).cancel(notificationId)
    }
}