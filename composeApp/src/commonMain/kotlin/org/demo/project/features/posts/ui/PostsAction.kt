package org.demo.project.features.posts.ui

sealed interface PostsAction {
    data object OnGetPosts : PostsAction
}