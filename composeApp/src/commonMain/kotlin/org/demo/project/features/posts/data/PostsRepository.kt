package org.demo.project.features.posts.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.demo.project.core.data.safeCall
import org.demo.project.core.domain.DataError
import org.demo.project.core.domain.Result
import org.demo.project.features.posts.data.network.PostsRemoteDataSource
import org.demo.project.features.posts.domain.IPostsRepository
import org.demo.project.features.posts.domain.model.Post


class PostsRepository(
    // This client is now GUARANTEED to have the base URL configured by Koin
    private val httpClient: HttpClient
): IPostsRepository {

    override suspend fun getSomePosts(): Result<List<Post>, DataError.Remote> {

        return safeCall {
            // 3. Make the call using the full URL.
            httpClient.get("posts").body()
        }
    }
}

// In .../features/posts/data/PostsRepositoryImpl.kt
/*class PostsRepository(
    private val remoteDataSource: PostsRemoteDataSource
) : IPostsRepository {
    override suspend fun getSomePosts(): Result<List<Post>, DataError.Remote> {
        // The repository is clean. It just delegates to the data source.
        return remoteDataSource.getPosts()
    }
}*/

