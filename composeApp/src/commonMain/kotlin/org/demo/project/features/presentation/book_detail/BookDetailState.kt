package org.demo.project.features.presentation.book_detail

import org.demo.project.features.domain.Book

data class BookDetailState(
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val book: Book? = null
)
