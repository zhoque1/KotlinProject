package org.demo.project.features.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import kotlinx.serialization.json.Json
import org.demo.project.features.posts.domain.model.Post
import org.demo.project.features.posts.presentation.screen.PostDetailScreen
import org.demo.project.features.presentation.screens.ChatDetailScreen
import org.demo.project.features.presentation.screens.ChatScreen
import org.demo.project.features.presentation.screens.HomeDetailScreen
import org.demo.project.features.presentation.screens.HomeScreen
import org.demo.project.features.presentation.screens.SettingsScreen

fun NavGraphBuilder.homeNav(navController: NavHostController) {
    navigation(
        route = Graph.HOME,
        startDestination = Routes.Home.route,
    ){
        composable(Routes.Home.route) {
            HomeScreen(
                navController
            )
        }
        composable(Routes.Chat.route) {
            ChatScreen(
                navController
            )
        }
        composable(Routes.Settings.route) {
            SettingsScreen(
                navController
            )
        }


        composable(Routes.HomeDetail.route) {
            HomeDetailScreen(
                navController, navController
            )
        }
        composable(Routes.ChatDetail.route) {
            ChatDetailScreen(
                navController, navController
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
    }
}