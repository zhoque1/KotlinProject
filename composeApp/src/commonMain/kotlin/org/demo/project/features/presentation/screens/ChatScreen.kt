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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.serialization.json.Json
import org.demo.project.features.posts.presentation.screen.PostsContent
import org.demo.project.features.posts.presentation.viewModel.PostsViewModel
import org.demo.project.features.navigation.Scaffold1Screen
import org.demo.project.features.navigation.Routes
import org.demo.project.features.posts.presentation.viewModel.PostListState
import org.demo.project.features.posts.presentation.viewModel.PostsEvent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ChatScreen(navController: NavHostController) {
    val postsViewModel = koinViewModel<PostsViewModel>()
    val state = postsViewModel.uiState.value

    LaunchedEffect(Unit){
        postsViewModel.setEvent(PostsEvent.OnGetPosts)
    }

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
                    "Chat Screen",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
                Button(onClick = { navController.navigate(Routes.About.route) }) {
                    Text(text = "About")
                }
                Button(onClick = { navController.navigate(Routes.HomeDetail.route) }) {
                    Text(text = "Navigate To Home Detail")
                }
                when{
                    state.isLoading ->{
                        println("IsLoading")
                        CircularProgressIndicator()
                    }
                }
                when(val postListState = state.postListState){
//                    is PostListState.IsLoading ->{
//                        println("IsLoading")
//                        CircularProgressIndicator()
//                    }
                    is PostListState.Error ->{
                        println("Error")
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 30.sp,
                            text = postListState.message,
                        )
                    }
                    is PostListState.PostsLoaded ->{
                        println("PostsLoaded")
                        PostsContent(
                            modifier = Modifier.fillMaxSize(),
                            list = postListState.posts,
                            onPostClick = {
                                // trying to send data through event and state change
                                postsViewModel.setEvent(PostsEvent.OnPostClick(post = it))

                                // trying to send data through route
                                val post = Json.encodeToString(it)
                                navController.navigate("post-detail_route/${post}")
                            }
                        )
                    }
                    else -> {
                        println("OnIdle")
                    }
                }
            }
        }
    }
}