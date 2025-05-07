package org.demo.project.features.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import org.demo.project.features.presentation.navigation.Routes
import org.demo.project.features.presentation.navigation.handleNavigationToTopDestination1

@Composable
fun ChatDetailScreen(navController: NavController, navHostController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Chat Detail Screen",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 20.dp)
            )
            Button(onClick = {
                navController.navigateUp()
            }) {
                Text(text = "Back")
            }
            Button(onClick = {
                navController.navigate(Routes.Chat.route){
                    handleNavigationToTopDestination1(this, navHostController)
                    navController.navigateUp()
                }
            }) {
                Text(text = "Back to Chat")
            }
        }
    }
}