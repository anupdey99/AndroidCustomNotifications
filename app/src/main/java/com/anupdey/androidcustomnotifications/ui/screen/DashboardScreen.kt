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
import com.anupdey.androidcustomnotifications.data.CarouselData
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            Template("Basic Notification") {
                showBasicNotification(context)
            }
            Template("Banner Notification") {
                showBannerNotification(context)
            }
            Template("Action Notification") {
                showActionNotification(context)
            }
            Template("Carousel Notification") {
                showCarouselNotification(context)
            }
        }
    }
}

@Composable
private fun Template(title: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Text(text = title)
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
        title = "Banner Notification Title",
        content = "Banner notification content",
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
        title = "Action Notification Title",
        content = "Action notification content",
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

private fun showCarouselNotification(context: Context) {

    val notificationData = NotificationData(
        type = NotificationType.CAROUSEL,
        title = "Carousel Notification Title",
        content = "Carousel notification content",
        url = "https://mygptest.grameenphone.com/mygpstatic/s1_cards_1706966153_3x.png",
        carousel = getCarouselList(),
        carouselIntervalInMillis = 3000,
        ticker = context.getString(R.string.app_name),
        channelId = context.getString(R.string.notification_channel_default),
        notificationId = 104
    )

    NotificationManager.process(context, notificationData)
}

private fun getCarouselList(): List<CarouselData> {
    val carouselList = mutableListOf<CarouselData>()
    carouselList.add(
        CarouselData(
            image = "https://mygptest.grameenphone.com/mygpapi/uploads/s1_cards_1584627647.png",
            url = "https://www.google.com"
        )
    )
    carouselList.add(
        CarouselData(
            image = "https://mygptest.grameenphone.com/mygpstatic/s1_cards_1706966153_3x.png",
            url = "https://www.google.com"
        )
    )
    carouselList.add(
        CarouselData(
            image = "https://mygptest.grameenphone.com/mygpstatic/s1_cards_1706434525_3x.png",
            url = "https://www.google.com"
        )
    )
    carouselList.add(
        CarouselData(
            image = "https://mygptest.grameenphone.com/mygpstatic/s1_cards_1706779012_3x.png",
            url = "https://www.google.com"
        )
    )
    carouselList.add(
        CarouselData(
            image = "https://fastly.picsum.photos/id/42/600/300.jpg?hmac=GwzAKUK6fQm3LDZrEhwkCWM-zSxRBPaAhLifpCJ9RZw",
            url = "https://www.google.com"
        )
    )
    carouselList.add(
        CarouselData(
            image = "https://fastly.picsum.photos/id/92/600/300.jpg?hmac=AYomWlz9wXpwr2LelD8tlQcLadspJLD_k8F-09V8DYk",
            url = "https://www.google.com"
        )
    )
    carouselList.add(
        CarouselData(
            image = "https://fastly.picsum.photos/id/1076/600/300.jpg?hmac=5mtmOdWwUGuKdoE0UvwIHBBzfgFdiWxikx-efYNyC-c",
            url = "https://www.google.com"
        )
    )
    carouselList.add(
        CarouselData(
            image = "https://fastly.picsum.photos/id/29/600/300.jpg?hmac=l9jCv8ZGxSvEs9HWv48lk-BZdUfFXq1qwf45PUBVB28",
            url = "https://www.google.com"
        )
    )
    carouselList.add(
        CarouselData(
            image = "https://fastly.picsum.photos/id/492/600/300.jpg?hmac=8XteBcKNL0qBCF8nGocxssmMI5oNuPe5uo3FObDi4eI",
            url = "https://www.google.com"
        )
    )
    carouselList.add(
        CarouselData(
            image = "https://fastly.picsum.photos/id/397/600/300.jpg?hmac=Fcvu5i0o1f-Mm1OD2Gq-bT6pXa3GODJnoWkF1_uvobI",
            url = "https://www.google.com"
        )
    )

    return carouselList
}