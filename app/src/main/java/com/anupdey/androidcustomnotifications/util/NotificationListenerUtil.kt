package com.anupdey.androidcustomnotifications.util

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.NotificationManagerCompat

object NotificationListenerUtil {

    private const val ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners"
    private const val FAILED_TO_GRANT_TOAST =
        "Failed to grant Notification Listener Service permission"

    //Notification service
    /*fun isNotificationServiceEnabled(context: Context): Boolean {
        val componentName = ComponentName(context, NotificationService::class.java)
        val flat =
            Settings.Secure.getString(context.contentResolver, ENABLED_NOTIFICATION_LISTENERS)
        return flat != null && flat.contains(componentName.flattenToString())
    }*/

    /*fun requestNotificationListenerPermission(
        context: Context,
        launcher: ActivityResultLauncher<Intent>
    ) {
        toggleNotificationListenerService(context, true)
        launcher.launch(getNotificationListenerSettingsIntent(context))
    }*/

    /*private fun toggleNotificationListenerService(context: Context, enable: Boolean) {
        val packageManager = context.packageManager
        packageManager.setComponentEnabledSetting(
            ComponentName(context, NotificationService::class.java),
            if (enable) PackageManager.COMPONENT_ENABLED_STATE_ENABLED else PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )
    }*/

    @SuppressLint("InlinedApi")
    private fun getNotificationListenerSettingsIntent(context: Context): Intent? {
        val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
        return if (intent.resolveActivity(context.packageManager) != null) {
            intent
        } else {
            Toast.makeText(context, FAILED_TO_GRANT_TOAST, Toast.LENGTH_SHORT).show()
            null
        }
    }

    fun isNotificationPermissionRequired(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            !NotificationManagerCompat.from(context).areNotificationsEnabled()
        } else false
    }

}