package com.lucienguimaraes.joke

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class JokeViewModel(private val jokeRepository: JokeRepository) : ViewModel() {

    val uiState = mutableStateOf(JokeUIState())

    fun fetchNewJoke() = viewModelScope.launch {
        uiState.value = uiState.value.copy(loading = true)
        jokeRepository
            .fetchJoke()
            .onSuccess { joke ->
                uiState.value = uiState.value.copy(loading = false, joke = joke)
            }
            .onFailure {
                uiState.value = uiState.value.copy(loading = false, error = true)
            }
    }
}

data class JokeUIState(
    val error: Boolean = false,
    val loading: Boolean = false,
    val joke: Joke? = null,
)