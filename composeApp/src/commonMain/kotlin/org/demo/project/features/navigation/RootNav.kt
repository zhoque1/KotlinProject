package org.demo.project.features.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kotlinx.serialization.json.Json
import org.demo.project.Route
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

        composable(Routes.PostDetail.route,
            arguments = listOf(
                navArgument(name = "post") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val postJson = backStackEntry.arguments?.getString("post")
            val post = Json.decodeFromString<Post>(postJson!!)
            PostDetailScreen(
                navController, post = post
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