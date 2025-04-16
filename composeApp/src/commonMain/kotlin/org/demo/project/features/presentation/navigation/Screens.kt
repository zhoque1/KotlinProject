package org.demo.project.features.presentation.navigation


sealed class Screens(val route: String) {
    data object Home : Screens("home_route")
    data object HomeDetail : Screens("home-detail_route")
    data object Chat : Screens("chat_route")
    data object ChatDetail : Screens("chat_detail_route")
    data object Settings : Screens("settings_route")
}