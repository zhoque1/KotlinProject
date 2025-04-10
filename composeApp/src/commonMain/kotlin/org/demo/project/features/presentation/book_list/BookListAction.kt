package org.demo.project.features.presentation.book_list

import org.demo.project.features.domain.Book


sealed interface BookListAction {
    data class OnSearchQueryChange(val query: String): BookListAction
    data class OnBookClick(val book: Book): BookListAction
    data class OnTabSelected(val index: Int): BookListAction
}