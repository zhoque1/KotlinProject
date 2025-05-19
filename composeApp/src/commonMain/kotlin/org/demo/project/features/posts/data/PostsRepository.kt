package org.demo.project.features.posts.data

import org.demo.project.core.domain.DataError
import org.demo.project.features.posts.data.network.IPostDataSource
import org.demo.project.features.posts.domain.model.Post
import org.demo.project.features.posts.domain.IPostsRepository
import org.demo.project.core.domain.Result

class PostsRepository(
    private val remoteDataSource: IPostDataSource
): IPostsRepository {
    override suspend fun getPosts(): List<Post> {
        val response = remoteDataSource.getPosts()
        return response //.map { it.toPost() }
    }
    override suspend fun getSomePosts(): Result<List<Post>, DataError.Remote>{
        val response = remoteDataSource.getSomePosts() //.map { item -> item.map { it.toPost() } }
        return response
    }
}