package org.demo.project.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import kotlinx.serialization.json.Json
import org.demo.project.Route
import org.demo.project.features.about.presentation.AboutScreen
import org.demo.project.features.posts.domain.model.Post
import org.demo.project.features.posts.presentation.screen.PostDetailScreen
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
        route = Graph.ROOT,
        startDestination = Graph.HOME,
    ) {
        composable(Routes.About.route) {
            AboutScreen(
                navController
            )
        }
        homeNav(navController = navController)
        listerNav(navController = navController)
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val LISTING = "listing_graph"
}