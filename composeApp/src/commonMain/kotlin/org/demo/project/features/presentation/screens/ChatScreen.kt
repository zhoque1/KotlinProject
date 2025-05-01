package org.demo.project.features.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import org.demo.project.features.gallery.domain.BookItem
import org.demo.project.features.gallery.ui.GalleryViewModel
import org.demo.project.features.posts.ui.PostsContent
import org.demo.project.features.gallery.ui.TestViewModel
import org.demo.project.features.presentation.navigation.Scaffold1Screen
import org.demo.project.features.presentation.navigation.Screens
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ChatScreen(navController: NavHostController) {
    val galleryViewModel = koinViewModel<GalleryViewModel>()
    val state by galleryViewModel.state.collectAsStateWithLifecycle()

//    val postsViewModel = koinViewModel<TestViewModel>()
//    val state by postsViewModel.state.collectAsStateWithLifecycle()

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
                Button(onClick = { navController.navigate(Screens.ChatDetail.route) }) {
                    Text(text = "Navigate To Chat Detail")
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
//                        PostsContent(modifier = Modifier.fillMaxSize(), list = state.posts)
                        GalleryContent(modifier = Modifier.fillMaxSize(), list = state.searchResults)
                    }
                }
            }
        }
    }
}

@Composable
fun GalleryContent(modifier: Modifier = Modifier, list: List<BookItem>) {

    LazyColumn(modifier.fillMaxSize()) {
        items(list) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 4.dp)
                    .fillMaxWidth()
            ) {
                Text(text = it.id.toString())
                Spacer(Modifier.height(4.dp))
                Text(text = it.title, style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.height(4.dp))
                it.coverKey?.let { it1 ->
                    Text(
                        text = it1,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

            }
        }
    }

}