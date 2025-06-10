package org.demo.project.features.navigation


import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.demo.project.features.about.presentation.AboutScreen

@Composable
fun NextRootNav(
    navController: NavHostController,
    logout: () -> Unit
//    = rememberNavController()
) {
    NavHost(
        navController = navController,
        route = Graph.NEXT_ROOT,
        startDestination = Graph.HOME,
    ){
        composable(route = AboutRoute.About.route) {
            AboutScreen(
                navController
            )
        }
        homeNav(navController = navController){
            logout()
        }
    }
}