package org.demo.project.features.presentation.screens.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import org.demo.project.features.presentation.navigation.ScaffoldScreen
import org.demo.project.features.presentation.navigation.SubNavConstants

@Composable
fun WidgetsScreen(navController: NavHostController) {
    ScaffoldScreen(navController = navController) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Widgets Screen")
            Button(onClick = { navController.navigate(SubNavConstants.WidgetsDetail.route) }) {
                Text(text = "Navigate to detail")
            }
        }
    }
}