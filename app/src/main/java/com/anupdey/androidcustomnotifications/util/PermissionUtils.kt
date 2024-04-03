package com.anupdey.androidcustomnotifications.util

import android.os.Build
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.anupdey.androidcustomnotifications.R

object PermissionUtils {
    private lateinit var notificationPermission: ActivityResultLauncher<String>

    fun registerAndRequestNotificationPermission(activity: ComponentActivity, listener: (() -> Unit)? = null) {
        registerActivityResult(activity, listener)
        requestNotificationPermission()
    }

    fun registerActivityResult(activity: ComponentActivity, listener: (() -> Unit)? = null) {
        notificationPermission = activity.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isPermissionGranted ->
            handleNotificationPermissionResult(activity, isPermissionGranted, listener)
        }
    }

    fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            notificationPermission.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun handleNotificationPermissionResult(activity: ComponentActivity, isGranted: Boolean, listener: (() -> Unit)? = null) {
        showPermissionStatus(activity, isGranted)
        if (isGranted) {
            listener?.invoke()
        }
    }

    private fun showPermissionStatus(activity: ComponentActivity, isGranted: Boolean) {
        val message = if (isGranted) {
            R.string.permission_accept
        } else {
            R.string.permission_denied
        }
        Toast.makeText(
            activity,
            activity.getString(message),
            Toast.LENGTH_SHORT
        ).show()
    }
}