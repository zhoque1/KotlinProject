package org.demo.project.features.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import org.demo.project.features.presentation.screens.ListingsScreen
import org.demo.project.features.presentation.screens.NotificationsScreen
import org.demo.project.features.presentation.screens.Settings2Screen

fun NavGraphBuilder.listerNav(navController: NavHostController) {
    navigation(
        route = Graph.LISTING,
        startDestination = Routes.Settings2.route
    ){
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