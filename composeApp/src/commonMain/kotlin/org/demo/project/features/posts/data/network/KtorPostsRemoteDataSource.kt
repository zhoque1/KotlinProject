package org.demo.project.features.posts.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.demo.project.core.data.safeCall
import org.demo.project.core.domain.DataError
import org.demo.project.core.domain.Result
import org.demo.project.features.posts.domain.model.Post

class KtorPostsRemoteDataSource(
    private val httpClient: HttpClient // Expects the "sports car" client
) : PostsRemoteDataSource {
    override suspend fun getPosts(): Result<List<Post>, DataError.Remote> {
        return safeCall {
            // Uses the car's GPS. Just needs the final turn: "posts"
            httpClient.get("posts").body()
        }
    }
}
