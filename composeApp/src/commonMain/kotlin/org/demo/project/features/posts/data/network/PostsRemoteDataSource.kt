package org.demo.project.features.posts.data.network

import org.demo.project.core.domain.DataError
import org.demo.project.core.domain.Result
import org.demo.project.features.posts.domain.model.Post

/**
 * Defines the contract for fetching posts data from a remote source.
 * The Repository will depend on this interface, not on a concrete implementation.
 */
interface PostsRemoteDataSource {
    /**
     * Fetches a list of posts from the remote API.
     * @return A [Result] object containing either a list of [Post] on success
     * or a [DataError.Remote] on failure.
     */
    suspend fun getPosts(): Result<List<Post>, DataError.Remote>
}
