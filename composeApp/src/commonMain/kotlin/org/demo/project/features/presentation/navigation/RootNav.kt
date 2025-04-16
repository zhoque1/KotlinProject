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
import org.demo.project.features.presentation.screens.SettingsScreen


@Composable
fun RootNav(navController: NavHostController, startDestination: Route) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route,
        modifier = Modifier
    ) {
        composable(Screens.Home.route) {
            HomeScreen(
                navController
            )
        }
        composable(Screens.HomeDetail.route) {
            HomeDetailScreen(
                navController, navController
            )
        }
        composable(Screens.Chat.route) {
            ChatScreen(
                navController
            )
        }
        composable(Screens.ChatDetail.route) {
            ChatDetailScreen(
                navController, navController
            )
        }
        composable(Screens.Settings.route) {
            SettingsScreen(
                navController
            )
        }
    }
}