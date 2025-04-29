package org.demo.project.features.gallery.data

import org.demo.project.core.domain.DataError
import org.demo.project.features.gallery.data.network.RemoteDataSource
import org.demo.project.features.gallery.domain.Post
import org.demo.project.features.gallery.domain.PostsRepository
import org.demo.project.core.domain.Result

class PostsRepositoryImp(
    private val remoteDataSource: RemoteDataSource
): PostsRepository {
    override suspend fun getPosts(): List<Post> {
        val response = remoteDataSource.getPosts()
        return response //.map { it.toPost() }
    }
    override suspend fun getSomePosts(): Result<List<Post>, DataError.Remote>{
        val response = remoteDataSource.getSomePosts() //.map { item -> item.map { it.toPost() } }
        return response
    }
}