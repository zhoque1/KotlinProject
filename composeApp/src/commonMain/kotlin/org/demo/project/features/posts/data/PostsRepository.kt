package org.demo.project.features.posts.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.demo.project.core.data.safeCall
import org.demo.project.core.domain.DataError
import org.demo.project.features.posts.domain.model.Post
import org.demo.project.features.posts.domain.IPostsRepository
import org.demo.project.core.domain.Result

private const val BASE_URL = "https://jsonplaceholder.typicode.com"
class PostsRepository(
    private val httpClient: HttpClient
): IPostsRepository {
//    override suspend fun getPosts(): List<Post> {
//        val response = httpClient
//            .get("${BASE_URL}/posts")
//            .body<List<Post>>()
//        return response //.map { it.toPost() }
//    }

//    override suspend fun getSomePosts(): Result<List<Post>, DataError.Remote>{
//        val response = safeCall<List<Post>> {
//            httpClient.get("$BASE_URL/posts")
//        } //.map { item -> item.map { it.toPost() } }
//        return response
//    }

    override suspend fun getSomePosts(): Result<List<Post>, DataError.Remote> =
        safeCall { httpClient.get("$BASE_URL/posts") }
}