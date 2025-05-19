package org.demo.project.features.posts.presentation.viewModel

import org.demo.project.features.posts.domain.model.Post

sealed interface PostsAction {
    data object OnIdle: PostsAction
    data object OnGetPosts: PostsAction
    data class OnPostClick(val post: Post): PostsAction
    data class OnSearchQueryChange(val query: String): PostsAction
}