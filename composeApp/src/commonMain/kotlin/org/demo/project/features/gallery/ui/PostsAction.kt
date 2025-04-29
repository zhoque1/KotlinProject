package org.demo.project.features.gallery.ui

sealed interface PostsAction {
    data object OnGetPosts : PostsAction
}