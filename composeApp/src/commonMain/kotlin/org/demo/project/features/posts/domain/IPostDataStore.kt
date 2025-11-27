package org.demo.project.features.posts.domain

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.demo.project.features.posts.domain.model.Post

/**
 * Defines a contract for a store that holds the currently selected post.
 */
interface IPostDataStore {
    /**
     * A flow representing the currently selected post, which can be null.
     */
    val selectedPost: StateFlow<Post?>

    /**
     * Updates the currently selected post.
     * @param post The post to be selected, or null to clear the selection.
     */
    fun setSelectedPost(post: Post?)
}

/**
 * A singleton implementation of IPostDataStore that holds the selected post in memory.
 */
class PostDataStore : IPostDataStore {
    private val _selectedPost = MutableStateFlow<Post?>(null)
    override val selectedPost: StateFlow<Post?> = _selectedPost.asStateFlow()

    override fun setSelectedPost(post: Post?) {
        _selectedPost.update { post }
    }
}