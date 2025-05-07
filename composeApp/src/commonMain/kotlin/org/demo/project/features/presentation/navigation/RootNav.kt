package org.demo.project.features.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.demo.project.Route
import org.demo.project.features.presentation.screens.ChatDetailScreen
import org.demo.project.features.presentation.screens.ChatScreen
import org.demo.project.features.presentation.screens.HomeDetailScreen
import org.demo.project.features.presentation.screens.HomeScreen
import org.demo.project.features.presentation.screens.ListingsScreen
import org.demo.project.features.presentation.screens.NotificationsScreen
import org.demo.project.features.presentation.screens.Settings2Screen
import org.demo.project.features.presentation.screens.SettingsScreen


@Composable
fun RootNav(navController: NavHostController, startDestination: Route) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route,
        modifier = Modifier
    ) {
        composable(Routes.Home.route) {
            HomeScreen(
                navController
            )
        }
        composable(Routes.HomeDetail.route) {
            HomeDetailScreen(
                navController, navController
            )
        }
        composable(Routes.Chat.route) {
            ChatScreen(
                navController
            )
        }
        composable(Routes.ChatDetail.route) {
            ChatDetailScreen(
                navController, navController
            )
        }
        composable(Routes.Settings.route) {
            SettingsScreen(
                navController
            )
        }




        composable(Routes.Listings.route) {
            ListingsScreen(
                navController
            )
        }
        composable(Routes.Notifications.route) {
            NotificationsScreen(
                navController
            )
        }
        composable(Routes.Settings2.route) {
            Settings2Screen(
                navController
            )
        }
    }
}