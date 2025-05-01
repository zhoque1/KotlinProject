package org.demo.project.features.gallery.domain

import org.demo.project.core.domain.DataError
import org.demo.project.core.domain.Result

interface GalleryRepository {
    suspend fun searchBooks(query: String): Result<BookList, DataError.Remote>
}