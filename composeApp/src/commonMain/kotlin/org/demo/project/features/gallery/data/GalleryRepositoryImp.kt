package org.demo.project.features.gallery.data

import org.demo.project.core.domain.DataError
import org.demo.project.core.domain.Result
import org.demo.project.features.gallery.data.network.GalleryDataSource
import org.demo.project.features.gallery.domain.BookList
import org.demo.project.features.gallery.domain.GalleryRepository

class GalleryRepositoryImp(
    private val galleryDataSource: GalleryDataSource
): GalleryRepository{
    override suspend fun searchBooks(query: String): Result<BookList, DataError.Remote> {
        val response = galleryDataSource.searchBooks(query)
        return response
    }
}