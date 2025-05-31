package org.demo.project.features.posts.presentation.viewModel

import org.demo.project.core.presentation.UiState
import org.demo.project.features.posts.domain.model.Post

data class PostsState (
    val postListState: PostListState,
    val postDetailState: PostDetailState,
    val isLoading: Boolean = true,
): UiState

sealed interface PostListState{
    data object OnIdle : PostListState
//    data object IsLoading : PostListState
    data class PostsLoaded(val posts: List<Post>) : PostListState
    data class Error(val message: String) : PostListState
}

sealed interface PostDetailState{
    data object OnIdle : PostDetailState
    data class PostClicked(val post: Post): PostDetailState
}