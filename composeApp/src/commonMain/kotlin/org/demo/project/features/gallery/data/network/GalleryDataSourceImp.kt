package org.demo.project.features.gallery.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.demo.project.core.data.safeCall
import org.demo.project.core.domain.DataError
import org.demo.project.core.domain.Result
import org.demo.project.features.gallery.domain.BookList

private const val BASE_URL = "https://openlibrary.org"

class GalleryDataSourceImp (
    private val httpClient: HttpClient
): GalleryDataSource{
    override suspend fun searchBooks(
        query: String,
        resultLimit: Int?
    ): Result<BookList, DataError.Remote> {
        return safeCall<BookList> {
            httpClient.get(
                urlString = "$BASE_URL/search.json"
            ) {
                parameter("q", query)
                parameter("limit", resultLimit)
                parameter("language", "eng")
                parameter("fields", "key,title,author_name,author_key,cover_edition_key,cover_i,ratings_average,ratings_count,first_publish_year,language,number_of_pages_median,edition_count")
            }
        }
    }
}