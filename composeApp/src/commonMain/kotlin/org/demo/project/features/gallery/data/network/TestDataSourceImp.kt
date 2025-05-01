package org.demo.project.features.gallery.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.demo.project.core.data.safeCall
import org.demo.project.core.domain.DataError
import org.demo.project.core.domain.Result
import org.demo.project.features.posts.data.network.RemoteDataSource
import org.demo.project.features.posts.domain.Post

private const val BASE_URL = "https://jsonplaceholder.typicode.com"

class TestDataSourceImp(
    private val httpClient: HttpClient
): TestDataSource {
    override suspend fun getSomePosts(): Result<List<Post>, DataError.Remote> {
        return safeCall<List<Post>> {
            httpClient.get("${BASE_URL}/posts")
        }
    }
}