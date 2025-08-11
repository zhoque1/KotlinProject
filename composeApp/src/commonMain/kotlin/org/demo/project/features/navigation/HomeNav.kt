package org.demo.project.features.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import org.demo.project.features.presentation.screens.ChatDetailScreen
import org.demo.project.features.presentation.screens.HomeDetailScreen
import org.demo.project.features.presentation.screens.HomeScreen
import org.demo.project.features.presentation.screens.SettingsScreen

fun NavGraphBuilder.homeNav(navController: NavHostController, logout: () -> Unit) {
    navigation(
        route = Graph.HOME,
        startDestination = HomeGraphRoute.Home.route,
    ){
        composable(HomeGraphRoute.Home.route) {
            HomeScreen(
                navController
            )
        }

        postsGraph(navController = navController)

//        composable(HomeGraphRoute.ChatNext.route) {
//            ChatScreen(
//                navController
//            )
//        }

//        composable(HomeGraphRoute.Chat.route) {
//            ChatNavGraph(navController = navController)
//        }

//        composable(HomeGraphRoute.Chat.route) {
//            ChatNavContent(
//                navController
//            )
//        }

        composable(HomeGraphRoute.Settings.route) {
            SettingsScreen(
                navController
            ){
                logout()
            }
        }



        composable(HomeGraphRoute.HomeDetail.route) {
            HomeDetailScreen(
                navController, navController
            )
        }
        composable(HomeGraphRoute.ChatDetail.route) {
            ChatDetailScreen(
                navController, navController
            )
        }

//        composable(HomeGraphRoute.PostDetail.route,
//            arguments = listOf(
//                navArgument(name = "post") {
//                    type = NavType.StringType
//                }
//            )
//        ) { backStackEntry ->
//            val postJson = backStackEntry.arguments?.getString("post")
//            val post = Json.decodeFromString<Post>(postJson!!)
//            PostDetailScreen(
//                navController, post = post
//            )
//        }

        listerNav(navController = navController)
    }
}

sealed class HomeGraphRoute(val route: String) {
    data object Home : HomeGraphRoute("home_route")
    data object Posts : HomeGraphRoute("posts_route")
    data object ChatNext : HomeGraphRoute("chat_next_route")
    data object Settings : HomeGraphRoute("settings_route")


    data object HomeDetail : HomeGraphRoute("home-detail_route")
    data object ChatDetail : HomeGraphRoute("chat_detail_route")
//    data object PostDetail : HomeGraphRoute("post-detail_route/{post}")
}