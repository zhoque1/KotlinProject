package org.demo.project.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.demo.project.Route
import org.demo.project.features.about.presentation.AboutScreen


@Composable
fun RootNav(navController: NavHostController, startDestination: Route) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME,
    ) {
        composable(route = AboutRoute.About.route) {
            AboutScreen(
                navController
            )
        }
        homeNav(navController = navController)
//        listerNav(navController = navController)
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val LISTING = "listing_graph"
    const val CHAT_GRAPH = "chat_graph"
}

sealed class AboutRoute(val route: String) {
    data object About : AboutRoute("about_route")
}