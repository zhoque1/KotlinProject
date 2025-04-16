package org.demo.project.features.presentation.navigation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycomposeapplication.ui.screens.*
import org.demo.project.core.presentation.SilverChalice
import org.demo.project.core.presentation.Turmeric
import org.demo.project.features.presentation.screens.animation.AnimationScreen
import org.demo.project.features.presentation.screens.template.TemplateScreen
import org.demo.project.features.presentation.screens.widget.WidgetsScreen
import org.jetbrains.compose.resources.stringResource


@Composable
fun NavComposeApp() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = NavConstants.Widgets.route) {
        //root screens
        composable(NavConstants.Widgets.route) {
            WidgetsScreen(navController)
        }
        composable(NavConstants.Animation.route) {
            AnimationScreen(navController)
        }
        composable(NavConstants.Template.route) {
            TemplateScreen(navController)
        }

        //sub screens
        composable(SubNavConstants.WidgetsDetail.route) {
            WidgetDetailScreen(navController)
        }
        composable(SubNavConstants.AnimationDetail.route) {
            AnimationDetailScreen(navController)
        }
        composable(SubNavConstants.TemplateDetail.route) {
            TemplateDetailScreen(navController)
        }
    }
}


@Composable
fun ScaffoldScreen(navController: NavHostController, screen: @Composable () -> Unit) {

    val bottomNavigationItems = listOf(
        NavConstants.Widgets,
        NavConstants.Animation,
        NavConstants.Template
    )


    Scaffold(topBar = {},
        bottomBar = { BottomAppNavBar(navController, bottomNavigationItems) },
        content = { screen() }
    )
}

@Composable
fun BottomAppNavBar(navController: NavHostController, bottomNavigationItems: List<NavConstants>) {

    BottomAppBar(
        containerColor = Color.White,
        contentColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 10.dp,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomNavigationItems.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

            NavigationBarItem(
                icon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
                label = {
                    if (isSelected) {
                        Text(text = screen.resourceId, color = Turmeric)
                    } else {
                        Text(text = screen.resourceId, color = Black)
                    }
                },
                selected = isSelected,
//                selectedContentColor = Turmeric,
//                unselectedContentColor = SilverChalice,
                modifier = if (isSelected) Modifier.drawBehind {
                    val strokeWidth = 6.dp.toPx()
                    //draw line of width 150px on top (hence y = 0f)
                    drawLine(
                        Turmeric,
                        Offset((size.width / 2.0f) - 75f, 0f),
                        Offset((size.width / 2.0f) + 75f, 0f),
                        strokeWidth,
                        cap = StrokeCap.Round
                    )
                } else Modifier,
                onClick = {
                    navController.navigate(screen.route) {
                        handleNavigationToTopDestination(this, navController)
                    }
                }
            )
        }
    }
}

/**
 * @param navOptionsBuilder handle Navigation To TopDestination that allows us to save the state of the screens
 * This is needed if you are navigating outside Bottom bar clicks to any bottom nav tabs
 * Example Home, Search and More
 */
fun handleNavigationToTopDestination(navOptionsBuilder: NavOptionsBuilder, navController: NavHostController) {
    // Pop up to the start destination of the graph to
    // avoid building up a large stack of destinations
    // on the back stack as users select items
    navOptionsBuilder.popUpTo(navController.graph.findStartDestination().id) {
        saveState = true
    }
    // Avoid multiple copies of the same destination when
    // reselecting the same item
    navOptionsBuilder.launchSingleTop = true
    // Restore state when reselecting a previously selected item
    navOptionsBuilder.restoreState = true
}