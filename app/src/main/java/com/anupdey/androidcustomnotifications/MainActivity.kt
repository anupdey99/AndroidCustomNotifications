package com.anupdey.androidcustomnotifications

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.anupdey.androidcustomnotifications.nav.NavHostMain
import com.anupdey.androidcustomnotifications.nav.Screen
import com.anupdey.androidcustomnotifications.ui.theme.AndroidCustomNotificationsTheme
import com.anupdey.androidcustomnotifications.util.PermissionUtils

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            AndroidCustomNotificationsTheme { SetupNavController(navController) }
            handleIntentData(intent)
        }

        PermissionUtils.registerAndRequestNotificationPermission(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntentData(intent)
    }

    private fun handleIntentData(intent: Intent?) {
        intent ?: return
        val route = intent.getStringExtra("route")
        when(route) {
            Screen.ResultScreen.route -> {
                navController.navigate(Screen.ResultScreen.route)
            }
        }
    }

    @Composable
    private fun SetupNavController(navController: NavHostController) {
        NavHostMain(navController = navController)
    }

}

