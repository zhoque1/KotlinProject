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
import org.demo.project.features.presentation.screens.PostsScreen

fun NavGraphBuilder.postsGraph(navController: NavHostController) {
    navigation(
        route = Graph.POSTS,
        startDestination = HomeGraphRoute.Posts.route
    ){
        composable(HomeGraphRoute.Posts.route) {
            PostsScreen(
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