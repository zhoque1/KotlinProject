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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.rememberPagerState
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
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.no_search_results
import org.demo.project.features.gallery.domain.BookItem
import org.demo.project.features.gallery.ui.GalleryState
import org.demo.project.features.gallery.ui.GalleryViewModel
import org.demo.project.features.presentation.navigation.Scaffold1Screen
import org.demo.project.features.presentation.navigation.Routes
import org.demo.project.features.product.ui.ProductScreen
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
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
                    "Home Screen",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
                Button(onClick = { navController.navigate(Routes.ChatDetail.route) }) {
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
//                        GalleryContent(modifier = Modifier.fillMaxSize(), list = state.searchResults, state = state)
                        ProductScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun GalleryContent(modifier: Modifier = Modifier, list: List<BookItem>, state: GalleryState) {
    val pagerState = rememberPagerState { 2 }
    val searchResultsListState = rememberLazyListState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if(state.isLoading) {
            CircularProgressIndicator()
        } else {
            when {
                state.errorMessage != null -> {
                    Text(
                        text = state.errorMessage.asString(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                state.searchResults.isEmpty() -> {
                    Text(
                        text = stringResource(Res.string.no_search_results),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                else -> {
                    LazyColumn(modifier.fillMaxSize()) {
                        items(
                            items = list,
                            key = {it.id}
                        ) {
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
            }
        }
    }



}