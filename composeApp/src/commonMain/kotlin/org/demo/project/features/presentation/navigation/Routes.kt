package org.demo.project.features.presentation.navigation


sealed class Routes(val route: String) {
    data object Home : Routes("home_route")
    data object HomeDetail : Routes("home-detail_route")
    data object Chat : Routes("chat_route")
    data object ChatDetail : Routes("chat_detail_route")
    data object Settings : Routes("settings_route")

    data object Listings : Routes("listings_route")
    data object Notifications : Routes("notifications_route")
    data object Settings2 : Routes("settings2_route")
}