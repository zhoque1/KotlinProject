package org.demo.project.features.posts.presentation.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.demo.project.core.domain.onError
import org.demo.project.core.domain.onSuccess
import org.demo.project.core.presentation.component.toUiText
import org.demo.project.features.posts.domain.IPostsRepository
import org.demo.project.features.posts.domain.useCase.IGetPostsUseCase
import org.demo.project.features.posts.domain.useCase.IGetSomePostsUseCase

class PostsViewModel(
    private val getPostsUseCase: IGetPostsUseCase,
    private val getSomePostsUseCase: IGetSomePostsUseCase
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

    fun onAction(action: PostsAction){
        when(action){
            is PostsAction.OnIdle ->{
            }
            is PostsAction.OnGetPosts -> {
            }
            is PostsAction.OnPostClick -> {
                _state.update {
                    it.copy(selectedPost = action.post)
                }
            }
            is PostsAction.OnSearchQueryChange -> {
            }
        }
    }
    private fun getPosts(){
        viewModelScope.launch(Dispatchers.IO) {
            getSomePostsUseCase.invoke()
                .onSuccess { posts ->
                    _state.update{
                        it.copy(
                            posts = posts,
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

    suspend fun getPostCount(): MutableState<Int> {
        exampleCount.value = getPostsUseCase.invoke().size
        return exampleCount
    }

    suspend fun getSomePostCount(): MutableState<Int> {
        getSomePostsUseCase.invoke()
            .onSuccess { posts ->
                exampleCount.value = posts.size
            }.onError { error ->
                errorMess.value = error.name
            }
        return exampleCount
    }
}