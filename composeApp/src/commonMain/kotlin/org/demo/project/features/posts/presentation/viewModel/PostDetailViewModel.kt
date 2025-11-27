package org.demo.project.features.posts.presentation.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow
import org.demo.project.features.posts.domain.IPostDataStore
import org.demo.project.features.posts.domain.model.Post

class PostDetailViewModel (
    private val postDataStore: IPostDataStore // Inject the data store
) : ViewModel() {

    val selectedPost: StateFlow<Post?> = postDataStore.selectedPost
}