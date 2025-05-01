package org.demo.project.features.gallery.ui

import org.demo.project.core.presentation.UiText
import org.demo.project.features.gallery.domain.BookItem

data class GalleryState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<BookItem> = emptyList(),
    val favoriteBooks: List<BookItem> = emptyList(),
    val isLoading: Boolean = true,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)
