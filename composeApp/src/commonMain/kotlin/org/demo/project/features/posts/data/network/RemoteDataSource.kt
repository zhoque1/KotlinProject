package org.demo.project.features.posts.data.network

import org.demo.project.core.domain.DataError
import org.demo.project.features.posts.domain.Post
import org.demo.project.core.domain.Result

interface RemoteDataSource{
    suspend fun getPosts():List<Post>
    suspend fun getSomePosts():Result<List<Post>, DataError.Remote>
}