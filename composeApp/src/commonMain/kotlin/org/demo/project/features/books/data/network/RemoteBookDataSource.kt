package org.demo.project.features.books.data.network

import org.demo.project.core.domain.DataError
import org.demo.project.core.domain.Result
import org.demo.project.features.books.data.dto.BookWorkDto
import org.demo.project.features.books.data.dto.SearchResponseDto

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>

    suspend fun getBookDetails(bookWorkId: String): Result<BookWorkDto, DataError.Remote>
}