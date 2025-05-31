package org.demo.project.features.posts.presentation.viewModel

import org.demo.project.core.presentation.UiEvent
import org.demo.project.features.posts.domain.model.Post

sealed interface PostsEvent: UiEvent {
    data object OnIdle: PostsEvent
    data object OnGetPosts: PostsEvent
    data class OnPostClick(val post: Post): PostsEvent
    data class OnSearchQueryChange(val query: String): PostsEvent
}