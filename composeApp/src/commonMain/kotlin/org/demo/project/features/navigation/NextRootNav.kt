package org.demo.project.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import org.demo.project.Route


@Composable
fun RootRootNav(navController: NavHostController, startDestination: Route) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HOME,
    ) {
        homeNav(navController = navController) {
            logout()
        }
//        listerNav(navController = navController)
    }
}
