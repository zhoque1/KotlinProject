package org.demo.project.features.posts.presentation.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
import org.demo.project.core.presentation.ComposeViewModel
import org.demo.project.core.presentation.SharedViewModel
import org.demo.project.core.presentation.component.toUiText
import org.demo.project.features.posts.domain.IPostDataStore
import org.demo.project.features.posts.domain.useCase.IGetPostsUseCase
import org.demo.project.features.posts.domain.useCase.IGetSomePostsUseCase

class PostsViewModel(
    private val getPostsUseCase: IGetPostsUseCase,
    private val getSomePostsUseCase: IGetSomePostsUseCase,
    private val postDataStore: IPostDataStore
): SharedViewModel<PostsState, PostsEvent>() {

    private val exampleCount: MutableState<Int> = mutableStateOf(1)
    private val errorMess = MutableStateFlow("")

    override fun createInitialState() = PostsState()

    override fun handleEvent(event: PostsEvent) {
        when(event){
            is PostsEvent.OnIdle ->{
                setState {
                    copy(postListState = PostListState.OnIdle)
                }
            }
            is PostsEvent.OnRefreshPosts -> {
                setState {
                    copy(postListState = PostListState.IsLoading)
                }
            }
            is PostsEvent.OnGetPosts -> {
                println("OnGetPosts")
                getPosts()
            }
            is PostsEvent.OnDisplayPosts -> {
                println("OnDisplayPosts")
                getPosts()
            }
            is PostsEvent.OnPostClick -> {
                setState {
                    copy(postDetailState  = PostDetailState.PostClicked(event.post))
                }
                postDataStore.setSelectedPost(event.post)
            }
            is PostsEvent.OnSearchQueryChange -> {
            }
        }
    }



    private fun getPosts(){
        viewModelScope.launch(Dispatchers.IO) {
            getSomePostsUseCase.invoke()
                .onSuccess { posts ->
                    println("PostsViewModel:")
                    setState {
                        copy(
                            postListState = PostListState.PostsLoaded(posts)
                        )
                    }
                }
                .onError { error ->
                    println("PostsViewModel: $error")
                    setState {
                        copy(postListState = PostListState.Error(error.name))
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