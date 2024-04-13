package com.anupdey.androidcustomnotifications.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anupdey.androidcustomnotifications.ui.screen.DashboardScreen
import com.anupdey.androidcustomnotifications.ui.screen.ResultScreen

sealed class Screen(val route: String) {
    object DashboardScreen : Screen("dashboard")
    object ResultScreen : Screen("result")
}

@Composable
fun NavHostMain(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.DashboardScreen.route) {
        composable(route = Screen.DashboardScreen.route) {
            DashboardScreen(navController)
        }

        composable(route = Screen.ResultScreen.route) {
            ResultScreen(navController)
        }

    }
}