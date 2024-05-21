package com.lucienguimaraes.joke

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucienguimaraes.datasource.JokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(private val jokeRepository: JokeRepository) : ViewModel() {

    val uiState = mutableStateOf(JokeUIState())

    fun fetchNewJoke() {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(loading = true)
            jokeRepository
                .fetchJoke()
                .onSuccess { joke ->
                    uiState.value = uiState.value.copy(
                        loading = false,
                        joke = joke.content
                    )
                }
                .onFailure {
                    uiState.value = uiState.value.copy(loading = false, error = true)
                }
        }
    }
}

data class JokeUIState(
    val error: Boolean = false,
    val loading: Boolean = false,
    val joke: String? = null,
)