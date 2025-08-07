package org.demo.project.features.posts.presentation.viewModel

import org.demo.project.core.presentation.Event
import org.demo.project.features.posts.domain.model.Post

sealed interface PostsEvent: Event {
    data object OnIdle: PostsEvent
    data object OnGetPosts: PostsEvent
    data class OnPostClick(val post: Post): PostsEvent
    data class OnSearchQueryChange(val query: String): PostsEvent
}