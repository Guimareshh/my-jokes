package com.lucienguimaraes.joke

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucienguimaraes.datasource.JokeRepository
import com.lucienguimaraes.datasource.entities.JokeEntity
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
                    uiState.value = uiState.value.copy(loading = false, joke = joke)
                }
                .onFailure {
                    uiState.value = uiState.value.copy(loading = false, error = true)
                }
        }
    }

    fun onFavoriteClick() {
        uiState.value = uiState.value.copy(loading = true)
        val joke = uiState.value.joke
        if (joke == null) {
            uiState.value = uiState.value.copy(loading = false, error = true)
        } else {
            if (joke.favorite) {
                deleteJoke(joke)
            } else {
                saveJoke(joke)
            }
        }
    }

    private fun saveJoke(joke: JokeEntity) {
        viewModelScope.launch {
            jokeRepository
                .saveJoke(joke)
                .onSuccess { savedJoke ->
                    uiState.value = uiState.value.copy(loading = false, joke = savedJoke)
                }
                .onFailure {
                    uiState.value = uiState.value.copy(loading = false, error = true)
                }
        }
    }

    private fun deleteJoke(joke: JokeEntity) {
        viewModelScope.launch {
            jokeRepository
                .deleteJoke(joke)
                .onSuccess { joke ->
                    uiState.value = uiState.value.copy(loading = false, joke = joke)
                }
                .onFailure {
                    uiState.value = uiState.value.copy(loading = false, error = true)
                }
        }
    }

    fun dismissError() {
        uiState.value = uiState.value.copy(error = false)
    }
}

data class JokeUIState(
    val error: Boolean = false,
    val loading: Boolean = false,
    val joke: JokeEntity? = null,
)
