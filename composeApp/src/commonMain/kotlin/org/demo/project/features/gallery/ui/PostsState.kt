package org.demo.project.features.gallery.ui

import org.demo.project.core.presentation.UiText
import org.demo.project.features.gallery.domain.Post

data class PostsState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: UiText? = null
)
