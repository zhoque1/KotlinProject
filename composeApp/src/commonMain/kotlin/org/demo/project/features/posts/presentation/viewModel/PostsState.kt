package org.demo.project.features.posts.presentation.viewModel

import org.demo.project.core.presentation.component.UiText
import org.demo.project.features.posts.domain.model.Post

data class PostsState(
    val selectedPost: Post? = null,
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: UiText? = null
)
