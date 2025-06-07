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
        startDestination = ListingGraphRoute.Settings2.route
    ){
        composable(ListingGraphRoute.Listings.route) {
            ListingsScreen(
                navController
            )
        }
        composable(ListingGraphRoute.Notifications.route) {
            NotificationsScreen(
                navController
            )
        }
        composable(ListingGraphRoute.Settings2.route) {
            Settings2Screen(
                navController
            )
        }
    }
}

sealed class ListingGraphRoute(val route: String){
    data object Listings : ListingGraphRoute("listings_route")
    data object Notifications : ListingGraphRoute("notifications_route")
    data object Settings2 : ListingGraphRoute("settings2_route")
}