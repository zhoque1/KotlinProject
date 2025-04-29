package org.demo.project

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.demo.project.features.presentation.navigation.BookNav
import org.demo.project.features.presentation.navigation.RootNav

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
//        BookNav(navController = navController, startDestination = Route.BookGraph)
//        BottomNav(navController = navController, startDestination = Route.BookGraph)
        RootNav(navController = navController, startDestination = Route.BookGraph)
//        NavComposeApp()

    }
}