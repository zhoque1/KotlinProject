package org.demo.project.features.presentation.navigation


sealed class NavConstants(val route: String, val resourceId: String){
    data object Widgets : NavConstants("WidgetsScreen", "Widget")
    data object Animation : NavConstants("AnimationScreen", "Anim")
    data object Template : NavConstants("TemplateScreen", "Tmplt")
}

sealed class SubNavConstants(val route: String){
    data object WidgetsDetail : SubNavConstants("WidgetDetail")
    data object AnimationDetail : SubNavConstants("AnimationDetail")
    data object TemplateDetail : SubNavConstants("TemplateDetail")
}