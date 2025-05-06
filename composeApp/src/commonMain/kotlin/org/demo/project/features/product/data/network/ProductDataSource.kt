package org.demo.project.features.product.data.network

import io.ktor.client.HttpClient
import org.demo.project.core.domain.DataError
import org.demo.project.core.domain.Result
import org.demo.project.features.posts.domain.Post
import org.demo.project.features.product.domain.ApiResponse

interface ProductDataSource {
    suspend fun getProducts(page: Int = 0) : Result<ApiResponse, DataError.Remote>
}