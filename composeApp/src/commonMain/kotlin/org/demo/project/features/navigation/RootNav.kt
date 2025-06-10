package org.demo.project.features.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.demo.project.Route
import org.demo.project.features.auth.authNavGraph

// important note: if you want to use nested navHost then you can't pass the navHostController for the nested navHost
@Composable
fun RootNav(navController: NavHostController, startDestination: Route) {
    val nextNavController = rememberNavController()
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION,
    ) {
        authNavGraph(navController = navController)

        composable(route = Graph.NEXT_ROOT) {
            NextRootNav(navController = nextNavController){
                navController.navigate(Graph.AUTHENTICATION)
            }
        }
//        homeNav(navController = navController)
//        listerNav(navController = navController)
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val NEXT_ROOT = "next_root_graph"
    const val HOME = "home_graph"
    const val LISTING = "listing_graph"
    const val CHAT_GRAPH = "chat_graph"
}

sealed class AboutRoute(val route: String) {
    data object About : AboutRoute("about_route")
}