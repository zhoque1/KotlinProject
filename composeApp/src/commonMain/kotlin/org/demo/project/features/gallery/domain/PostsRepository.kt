package org.demo.project.features.gallery.domain

import org.demo.project.core.domain.DataError
import org.demo.project.core.domain.Result


interface PostsRepository {
    suspend fun getPosts():List<Post>
    suspend fun getSomePosts(): Result<List<Post>, DataError.Remote>
}