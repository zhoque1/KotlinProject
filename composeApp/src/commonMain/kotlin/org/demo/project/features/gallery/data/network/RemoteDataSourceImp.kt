package org.demo.project.features.gallery.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.demo.project.core.data.safeCall
import org.demo.project.core.domain.DataError
import org.demo.project.features.gallery.domain.Post
import org.demo.project.core.domain.Result

private const val BASE_URL = "https://jsonplaceholder.typicode.com"

class RemoteDataSourceImp(
    private val httpClient: HttpClient
): RemoteDataSource {
    override suspend fun getPosts():List<Post>{
        return httpClient
            .get("$BASE_URL/posts")
            .body<List<Post>>()
    }

    override suspend fun getSomePosts(): Result<List<Post>, DataError.Remote> {
        return safeCall<List<Post>> {
            httpClient.get("$BASE_URL/posts")
        }
    }
}