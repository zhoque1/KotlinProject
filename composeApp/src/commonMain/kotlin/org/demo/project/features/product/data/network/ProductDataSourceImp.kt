package org.demo.project.features.product.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import org.demo.project.features.product.domain.ApiResponse

object ProductDataSourceImp {
    suspend fun HttpClient.getProducts(page: Int = 0) =
        getResults<ApiResponse> {
            url("https://dummyjson.com/products?limit=10&skip=${page * 10}")
            method = HttpMethod.Get
        }
}