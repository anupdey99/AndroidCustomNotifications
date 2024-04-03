package com.anupdey.androidcustomnotifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.anupdey.androidcustomnotifications.data.NotificationData
import com.anupdey.androidcustomnotifications.ui.theme.AndroidCustomNotificationsTheme
import com.anupdey.androidcustomnotifications.util.PermissionUtils
import com.anupdey.androidcustomnotifications.util.createNotification
import com.anupdey.androidcustomnotifications.util.createNotificationChannel
import com.anupdey.androidcustomnotifications.util.showNotification

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AndroidCustomNotificationsTheme { NotificationTemplate() } }

        PermissionUtils.registerAndRequestNotificationPermission(this)
    }

    @Composable
    private fun NotificationTemplate() {
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
                        showBasicNotification()
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

                    }) {
                    Text(text = "Dismiss")
                }
            }
        }
    }

    private fun showBasicNotification() {
        val channelId = getString(R.string.notification_channel_default)
        createNotificationChannel(this, channelId)

        val notification = NotificationData(
            title = "Basic Notification Title",
            content = "Basic notification content",
            ticker = getString(R.string.app_name),
            channelId = getString(R.string.notification_channel_default),
            notificationId = 101
        )

        val notificationBuilder = createNotification(this, notification)
        showNotification(this, 123, notificationBuilder.build())
    }

}

