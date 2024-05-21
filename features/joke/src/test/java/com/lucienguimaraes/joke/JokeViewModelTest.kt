package com.lucienguimaraes.joke

import com.lucienguimaraes.datasource.entities.JokeEntity
import com.lucienguimaraes.datasource.JokeRepository
import com.lucienguimaraes.datasource.entities.jokeEntityNoFavorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class JokeViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `Given a click on fetch joke it should get a new joke`() {
        class TestJokeRepository : JokeRepository {
            override suspend fun fetchJoke(): Result<JokeEntity> = Result.success(
                jokeEntityNoFavorite()
            )
        }

        val viewModel = JokeViewModel(TestJokeRepository())
        assertEquals(JokeUIState(), viewModel.uiState.value)
        viewModel.fetchNewJoke()
        assertEquals(JokeUIState(joke = jokeEntityNoFavorite()), viewModel.uiState.value)
    }

    @Test
    fun `Given a click on fetch joke that fail it should set an error on ui state`() {
        class TestJokeRepository : JokeRepository {
            override suspend fun fetchJoke(): Result<JokeEntity> = Result.failure(Exception())
        }

        val viewModel = JokeViewModel(TestJokeRepository())
        assertEquals(JokeUIState(), viewModel.uiState.value)
        viewModel.fetchNewJoke()
        assertEquals(JokeUIState(error = true), viewModel.uiState.value)
    }

    @Test
    fun `Given a dismiss on error it should remove the error from the state`() {
        class TestJokeRepository : JokeRepository {
            override suspend fun fetchJoke(): Result<JokeEntity> = Result.failure(Exception())
        }

        val viewModel = JokeViewModel(TestJokeRepository())
        assertEquals(JokeUIState(), viewModel.uiState.value)
        viewModel.fetchNewJoke()
        assertEquals(JokeUIState(error = true), viewModel.uiState.value)
        viewModel.dismissError()
        assertEquals(JokeUIState(), viewModel.uiState.value)
    }
}