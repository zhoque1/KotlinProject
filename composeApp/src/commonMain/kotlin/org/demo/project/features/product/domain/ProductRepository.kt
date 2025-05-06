package org.demo.project.features.product.domain

import app.cash.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(): Flow<PagingData<Products>>
}