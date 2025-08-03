package org.demo.project.features.posts.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import org.demo.project.features.posts.domain.model.Post
import org.demo.project.features.posts.presentation.viewModel.PostDetailState
import org.demo.project.features.posts.presentation.viewModel.PostsViewModel
import org.koin.compose.viewmodel.koinViewModel

//import com.loopnet.android.features.demo.presentation.viewmodel.GlobalViewModel

@Composable
fun PostDetailScreen(
    navController: NavController,
    post: Post,
) {
    val postsViewModel = koinViewModel<PostsViewModel>()
    val state = postsViewModel.uiState.value

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                navController.navigateUp()
            }) {
                Text(text = "Back")
            }
            Text(
                "Post Detail Screen",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 20.dp)
            )

            // this section will work if your view model is declared as singleton
            when(val postDetailState = state.postDetailState){
                is PostDetailState.PostClicked ->{
                    println("PostClicked")
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = postDetailState.post.id.toString())
                        Spacer(Modifier.height(4.dp))
                        Text(text = postDetailState.post.title, style = MaterialTheme.typography.bodyLarge)
                        Spacer(Modifier.height(4.dp))
                        Text(text = postDetailState.post.body, style = MaterialTheme.typography.bodySmall)

                    }
                }
                else -> {
                    println("OnIdle")
                }
            }

            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 4.dp)
                    .fillMaxWidth()
            ) {
                Text(text = post.id.toString())
                Spacer(Modifier.height(4.dp))
                Text(text = post.title, style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.height(4.dp))
                Text(text = post.body, style = MaterialTheme.typography.bodySmall)

            }
        }
    }
}