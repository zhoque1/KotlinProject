package org.demo.project.features.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.demo.project.features.gallery.ui.GalleryContent
import org.demo.project.features.gallery.ui.PostsViewModel
import org.demo.project.features.presentation.navigation.Scaffold1Screen
import org.demo.project.features.presentation.navigation.Screens
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
    val postsViewModel = koinViewModel<PostsViewModel>()
    val state by postsViewModel.state.collectAsStateWithLifecycle()

    Scaffold1Screen(navController = navController){
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
                    "Home Screen",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
                Button(onClick = { navController.navigate(Screens.HomeDetail.route) }) {
                    Text(text = "Navigate To Home Detail")
                }
                when{
                    state.isLoading ->{
                        CircularProgressIndicator()
                    }
                    state.errorMessage != null ->{
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 30.sp,
                            text = state.errorMessage!!.asString(),
                        )
                    }
                    else ->{
                        GalleryContent(modifier = Modifier.fillMaxSize(), list = state.posts)
                    }
                }
            }
        }
    }
}