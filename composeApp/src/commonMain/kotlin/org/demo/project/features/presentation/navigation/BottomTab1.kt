package org.demo.project.features.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.currentBackStackEntryAsState
import org.demo.project.features.presentation.theme.M3BottomNavigationTheme



@Composable
fun Scaffold1Screen(navController: NavHostController, screen: @Composable () -> Unit) {
    val bottomBarItem = listOf(
        BottomTabNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
            route = Routes.Home.route
        ),
        BottomTabNavigationItem(
            title = "Chat",
            selectedIcon = Icons.Filled.Email,
            unselectedIcon = Icons.Outlined.Email,
            hasNews = false,
            badgeCount = 45,
            route = Routes.Chat.route
        ),
        BottomTabNavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            hasNews = true,
            route = Routes.Settings.route
        ),
    )
    Scaffold(topBar = {},
        bottomBar = { BottomTab(navController, bottomBarItem) },
        content = { screen() }
    )
}

@Composable
fun BottomTab(navController: NavHostController, items: List<BottomTabNavigationItem>) {
    M3BottomNavigationTheme{
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
        NavigationBar {
            items.forEachIndexed { index, item ->
                val isSelected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(item.route) {
                            handleNavigationToTopDestination1(this, navController)
                        }
                    },
                    label = {
                        if (isSelected) {
                            Text(text = item.title, color = Black)
                        } else {
                            Text(text = item.title)
                        }
                    },
//                                alwaysShowLabel = false,
                    icon = {
                        BadgedBox(
                            badge = {
                                if(item.badgeCount != null) {
                                    Badge {
                                        Text(text = item.badgeCount.toString())
                                    }
                                } else if(item.hasNews) {
                                    Badge()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (isSelected) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        }
                    },
                )
            }
        }
    }
}

/**
 * @param navOptionsBuilder handle Navigation To TopDestination that allows us to save the state of the screens
 * This is needed if you are navigating outside Bottom bar clicks to any bottom nav tabs
 * Example Home, Search and More
 */
fun handleNavigationToTopDestination1(navOptionsBuilder: NavOptionsBuilder, navController: NavHostController) {
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