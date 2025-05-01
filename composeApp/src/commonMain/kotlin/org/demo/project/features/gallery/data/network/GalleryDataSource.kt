package org.demo.project.features.gallery.data.network

import org.demo.project.core.domain.DataError
import org.demo.project.core.domain.Result
import org.demo.project.features.gallery.domain.BookList

interface GalleryDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<BookList, DataError.Remote>
}