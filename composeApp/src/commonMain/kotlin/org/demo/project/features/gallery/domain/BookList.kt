package org.demo.project.features.gallery.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookList(
    @SerialName("docs") val results: List<BookItem>
)
