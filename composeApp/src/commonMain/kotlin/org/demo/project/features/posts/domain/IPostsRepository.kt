package org.demo.project.features.posts.domain

import org.demo.project.core.domain.DataError
import org.demo.project.core.domain.Result
import org.demo.project.features.posts.domain.model.Post


interface IPostsRepository {
    suspend fun getPosts():List<Post>
    suspend fun getSomePosts(): Result<List<Post>, DataError.Remote>
}