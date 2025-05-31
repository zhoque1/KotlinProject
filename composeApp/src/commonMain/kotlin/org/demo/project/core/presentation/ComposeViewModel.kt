package org.demo.project.core.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * ToDo update BaseViewModel to move away from anti-patterns, and cleanup resources
 * https://medium.com/androiddevelopers/viewmodel-one-off-event-antipatterns-16a1da869b95
  https://developer.android.com/topic/architecture/ui-layer/events#other-use-cases
  https://proandroiddev.com/android-singleliveevent-redux-with-kotlin-flow-b755c70bb055
 */
abstract class ComposeViewModel<
        Event : UiEvent,
        State : UiState
//        , Effect : UiEffect
        > : ViewModel() {

    // Create Initial State of View
    private val initialState : State by lazy { createInitialState() }
    abstract fun createInitialState() : State

    // Get Current State
    private val currentState: State
        get() = uiState.value

    private val _uiState : MutableState<State> = mutableStateOf(initialState)
    val uiState:androidx.compose.runtime.State<State> = _uiState

    private val _event : MutableSharedFlow<Event> = MutableSharedFlow()
    private val event = _event.asSharedFlow()

//    private val _effect : Channel<Effect> = Channel()
//    val effect = _effect.receiveAsFlow()

    init {
        subscribeEvents()
    }

    /**
     * Start listening to Event
     */
    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect {
                handleEvent(it)
            }
        }
    }

    /**
     * Handle each event
     */
    abstract fun handleEvent(event : Event)

    /**
     * Set new Event
     */
    fun setEvent(event : Event) {
        val newEvent = event
        viewModelScope.launch { _event.emit(newEvent) }
    }


    /**
     * Set new Ui State
     */
    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    /**
     * Set new Effect
     */
//    @Deprecated("Deprecated set effect, please use state instead")
//    protected fun setEffect(builder: () -> Effect) {
//        val effectValue = builder()
//        viewModelScope.launch { _effect.send(effectValue) }
//    }
}