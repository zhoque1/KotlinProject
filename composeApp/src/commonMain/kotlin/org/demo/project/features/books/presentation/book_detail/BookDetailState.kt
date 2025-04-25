package org.demo.project.features.books.presentation.book_detail

import org.demo.project.features.books.domain.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null
)
