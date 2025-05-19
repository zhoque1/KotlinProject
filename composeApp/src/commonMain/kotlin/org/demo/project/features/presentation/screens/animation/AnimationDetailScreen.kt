package com.example.mycomposeapplication.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.demo.project.features.navigation.NavConstants

@Composable
fun AnimationDetailScreen(navController: NavController) {
    Column {
        Button(onClick = { navController.navigate(NavConstants.Animation.route) }) {
            Text(text = "Back to Animation")
        }
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            "Animation Screen",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 20.dp)
        )
    }
}