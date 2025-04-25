package org.demo.project

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.demo.project.features.presentation.SelectedBookViewModel
import org.demo.project.features.presentation.book_detail.BookDetailAction
import org.demo.project.features.presentation.book_detail.BookDetailScreenRoot
import org.demo.project.features.presentation.book_detail.BookDetailViewModel
import org.demo.project.features.presentation.book_list.BookListScreenRoot
import org.demo.project.features.presentation.book_list.BookListViewModel
import org.demo.project.features.presentation.navigation.BookNav
import org.demo.project.features.presentation.navigation.BottomNav
import org.demo.project.features.presentation.navigation.NavComposeApp
import org.demo.project.features.presentation.navigation.RootNav
import org.demo.project.features.presentation.navigation.Screens
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        BookNav(navController = navController, startDestination = Route.BookGraph)
//        BottomNav(navController = navController, startDestination = Route.BookGraph)
//        RootNav(navController = navController, startDestination = Route.BookGraph)
//        NavComposeApp()

    }
}