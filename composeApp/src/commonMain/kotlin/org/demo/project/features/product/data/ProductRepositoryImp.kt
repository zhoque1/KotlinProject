package org.demo.project.features.product.data

import app.cash.paging.Pager
import app.cash.paging.PagingData
import app.cash.paging.PagingConfig
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import org.demo.project.features.product.data.network.ProductDataSourceImp.getProducts
import org.demo.project.features.product.data.network.ResultPagingSource
import org.demo.project.features.product.data.network.map
import org.demo.project.features.product.domain.ProductRepository
import org.demo.project.features.product.domain.Products


class ProductRepositoryImp(
    private val httpClient: HttpClient
): ProductRepository {
    override fun getProducts(): Flow<PagingData<Products>> = Pager(
        config = PagingConfig(pageSize = 10, initialLoadSize = 10, enablePlaceholders = false,),
        pagingSourceFactory = {
            ResultPagingSource { page, _ ->
                delay(800)
                httpClient.getProducts(page).map { it.list }
            }
        }
    ).flow
}