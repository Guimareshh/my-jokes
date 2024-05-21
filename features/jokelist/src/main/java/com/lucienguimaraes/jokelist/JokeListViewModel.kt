package com.lucienguimaraes.jokelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucienguimaraes.datasource.JokeRepository
import com.lucienguimaraes.datasource.entities.JokeEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeListViewModel @Inject constructor(private val jokeRepository: JokeRepository) : ViewModel() {

    val uiState: StateFlow<JokeListUIState> = jokeRepository
        .listenAllJoke()
        .map { jokeList -> JokeListUIState(jokeList = jokeList) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = JokeListUIState(),
        )

    fun onDeleteJoke(jokeId: Long) {
        viewModelScope.launch {
            jokeRepository.deleteJoke(jokeId)
        }
    }
}

data class JokeListUIState(
    val error: Boolean = false,
    val loading: Boolean = false,
    val jokeList: List<JokeEntity> = emptyList(),
)
