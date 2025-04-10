package org.demo.project.features.presentation.book_list

import org.demo.project.core.presentation.UiText
import org.demo.project.features.domain.Book


data class BookListState(
    val searchQuery: String = "Kotlin",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading: Boolean = true,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null
)