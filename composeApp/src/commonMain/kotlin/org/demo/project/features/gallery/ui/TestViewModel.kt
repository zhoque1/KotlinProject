package org.demo.project.features.gallery.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.demo.project.core.domain.onError
import org.demo.project.core.domain.onSuccess
import org.demo.project.core.presentation.toUiText
import org.demo.project.features.gallery.domain.TestRepository
import org.demo.project.features.posts.ui.PostsState

class TestViewModel(
    private val postsRepository: TestRepository
): ViewModel() {

    private val exampleCount: MutableState<Int> = mutableStateOf(1)
    private val errorMess = MutableStateFlow("")

    private val _state = MutableStateFlow(PostsState())
    val state = _state
        .onStart {
            getPosts()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )
    private fun getPosts(){
        viewModelScope.launch {
            postsRepository
                .getSomePosts()
                .onSuccess { searchResults ->
                    _state.update{
                        it.copy(
                            posts = searchResults,
                            isLoading = false,
                        )
                    }
                }
                .onError { error ->
                    _state.update{
                        it.copy(
                            posts = emptyList(),
                            isLoading = false,
                            errorMessage = error.toUiText()
                        )
                    }
                }
        }
    }

//    suspend fun getPostCount(): MutableState<Int> {
//        exampleCount.value = postsRepository.getPosts().size
//        return exampleCount
//    }
//
//    suspend fun getSomePostCount(): MutableState<Int> {
//            postsRepository
//                .getSomePosts()
//                .onSuccess { posts ->
//                    exampleCount.value = posts.size
//                }.onError { error ->
//                    errorMess.value = error.name
//                }
//        return exampleCount
//    }
}