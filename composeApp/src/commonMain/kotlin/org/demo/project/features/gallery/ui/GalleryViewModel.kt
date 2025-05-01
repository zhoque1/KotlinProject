package org.demo.project.features.gallery.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.demo.project.core.domain.onError
import org.demo.project.core.domain.onSuccess
import org.demo.project.core.presentation.toUiText
import org.demo.project.features.gallery.domain.GalleryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class GalleryViewModel(
    private val galleryRepository: GalleryRepository
): ViewModel() {

    private val exampleCount: MutableState<Int> = mutableStateOf(1)
    private val errorMess = MutableStateFlow("")

    private val _state = MutableStateFlow(GalleryState())
    val state = _state
        .onStart {
            searchBooks("Kotlin")
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    private fun searchBooks(query: String) {
        viewModelScope.launch {
            galleryRepository
                .searchBooks(query)
                .onSuccess { searchResults ->
                    _state.update{
                        it.copy(
                            isLoading = false,
                            errorMessage = null,
                            searchResults = searchResults.results,
                        )
                    }
                }
                .onError { error ->
                    _state.update{
                        it.copy(
                            searchResults = emptyList(),
                            isLoading = false,
                            errorMessage = error.toUiText()
                        )
                    }
                }
        }
    }
}