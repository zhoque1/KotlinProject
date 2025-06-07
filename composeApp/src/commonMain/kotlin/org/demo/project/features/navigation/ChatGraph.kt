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
import org.demo.project.features.posts.presentation.viewModel.PostsViewModel
import org.demo.project.features.presentation.screens.ChatScreen
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.chatGraph(navController: NavHostController) {
    navigation(
        route = Graph.CHAT_GRAPH,
        startDestination = HomeGraphRoute.Chat.route
    ){
        composable(HomeGraphRoute.Chat.route) {
            ChatScreen(
                navController
            )
        }

        composable(PostDetailGraphRoute.PostDetail.route,
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

sealed class PostDetailGraphRoute(val route: String){
    data object PostDetail : PostDetailGraphRoute("post-detail_route/{post}")
}