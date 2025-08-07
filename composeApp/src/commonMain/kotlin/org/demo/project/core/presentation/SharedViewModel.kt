package org.demo.project.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class SharedViewModel<state: State, event: Event>:ViewModel() {

    // Create Initial State of View
    private val initialState : state by lazy { createInitialState() }

    private val _uiState: MutableStateFlow<state> = MutableStateFlow(initialState)
    val uiState: StateFlow<state> = _uiState

    private val _uiEvent = MutableSharedFlow<event>()

    // Get Current State
    private val currentState: state
        get() = uiState.value

    init {
        viewModelScope.launch {
            _uiEvent.collect { event ->
                listenEvents(event)
            }
        } }

    fun postEvent(e: event) {
        viewModelScope.launch {
            _uiEvent.emit(e)
        }
    }

    protected fun updateState(reduce: state.() -> state) {
        viewModelScope.launch {
            val newState = currentState.reduce()
            _uiState.emit(newState)
        }

    }
    abstract fun listenEvents(event: event)


    abstract fun createInitialState(): state

}