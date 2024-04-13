package com.anupdey.androidcustomnotifications.ui.screen

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.anupdey.androidcustomnotifications.MainActivity
import com.anupdey.androidcustomnotifications.R
import com.anupdey.androidcustomnotifications.data.NotificationData
import com.anupdey.androidcustomnotifications.nav.Screen
import com.anupdey.androidcustomnotifications.util.NotificationBroadcastReceiver
import com.anupdey.androidcustomnotifications.util.NotificationManager
import com.anupdey.androidcustomnotifications.util.NotificationType
import com.anupdey.androidcustomnotifications.util.getPendingFlags

@Composable
fun DashboardScreen(navController: NavHostController) {
    val context = LocalContext.current
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    showBasicNotification(context)
                }) {
                Text(text = "Basic Notification", modifier = Modifier.semantics {
                    contentDescription = "This is custom description"
                })
            }
            Spacer(modifier = Modifier.height(4.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    showBannerNotification(context)
                }) {
                Text(text = "Banner Notification",)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    showActionNotification(context)
                }) {
                Text(text = "Action Notification",)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {

                }) {
                Text(text = "Dismiss")
            }
        }
    }
}

private fun showBasicNotification(context: Context) {
    val notificationData = NotificationData(
        type = NotificationType.DEFAULT,
        title = "Basic Notification Title",
        content = "Basic notification content",
        ticker = context.getString(R.string.app_name),
        channelId = context.getString(R.string.notification_channel_default),
        notificationId = 101
    )

    NotificationManager.process(context, notificationData)
}

private fun showBannerNotification(context: Context) {
    val notificationData = NotificationData(
        type = NotificationType.BANNER,
        title = "Basic Notification Title",
        content = "Basic notification content",
        url = "https://mygptest.grameenphone.com/mygpapi/uploads/s1_cards_1584627647.png",
        ticker = context.getString(R.string.app_name),
        channelId = context.getString(R.string.notification_channel_default),
        notificationId = 102
    )

    NotificationManager.process(context, notificationData)
}

private fun showActionNotification(context: Context) {

    val intent1 = Intent(context, NotificationBroadcastReceiver::class.java).apply {
        putExtra("route", Screen.ResultScreen.route)
        putExtra("data", "Action1")
        putExtra("notificationId", 103)
    }
    val pendingIntent1 = PendingIntent.getBroadcast(context, 1031, intent1, getPendingFlags())

    val intent2 = Intent(context, NotificationBroadcastReceiver::class.java).apply {
        putExtra("route", Screen.ResultScreen.route)
        putExtra("data", "Action2")
        putExtra("notificationId", 103)
    }
    val pendingIntent2 = PendingIntent.getBroadcast(context, 1032, intent2, getPendingFlags())

    val notificationData = NotificationData(
        type = NotificationType.ACTION,
        title = "Basic Notification Title",
        content = "Basic notification content",
        url = "https://mygptest.grameenphone.com/mygpapi/uploads/s1_cards_1584627647.png",

        actionId = R.drawable.ic_notification,
        action1Name = "Action1",
        action1PendingIntent = pendingIntent1,
        action2Name = "Action2",
        action2PendingIntent = pendingIntent2,

        ticker = context.getString(R.string.app_name),
        channelId = context.getString(R.string.notification_channel_default),
        notificationId = 103
    )

    NotificationManager.process(context, notificationData)
}