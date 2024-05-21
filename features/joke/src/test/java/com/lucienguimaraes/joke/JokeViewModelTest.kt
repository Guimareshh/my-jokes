package com.lucienguimaraes.joke

import com.lucienguimaraes.datasource.JokeRepository
import com.lucienguimaraes.datasource.entities.JokeEntity
import com.lucienguimaraes.datasource.entities.jokeEntityFavorite
import com.lucienguimaraes.datasource.entities.jokeEntityNoFavorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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

    //region fetch joke
    @Test
    fun `Given a click on fetch joke it should get a new joke`() {

        val viewModel = JokeViewModel(SuccessJokeRepository())
        assertEquals(JokeUIState(), viewModel.uiState.value)
        viewModel.fetchNewJoke()
        assertEquals(JokeUIState(joke = jokeEntityNoFavorite()), viewModel.uiState.value)
    }

    @Test
    fun `Given a click on fetch joke that fail it should set an error on ui state`() {
        val viewModel = JokeViewModel(ErrorJokeRepository())
        assertEquals(JokeUIState(), viewModel.uiState.value)
        viewModel.fetchNewJoke()
        assertEquals(JokeUIState(error = true), viewModel.uiState.value)
    }

    @Test
    fun `Given a dismiss on error it should remove the error from the state`() {
        val viewModel = JokeViewModel(ErrorJokeRepository())
        assertEquals(JokeUIState(), viewModel.uiState.value)
        viewModel.fetchNewJoke()
        assertEquals(JokeUIState(error = true), viewModel.uiState.value)
        viewModel.dismissError()
        assertEquals(JokeUIState(), viewModel.uiState.value)
    }
    //endregion

    //region favorite click
    @Test
    fun `Given a click on favorite it should save the joke`() {
        val viewModel = JokeViewModel(SuccessJokeRepository())
        assertEquals(JokeUIState(), viewModel.uiState.value)
        viewModel.fetchNewJoke()
        assertEquals(JokeUIState(joke = jokeEntityNoFavorite()), viewModel.uiState.value)
        viewModel.onFavoriteClick()
        assertEquals(JokeUIState(joke = jokeEntityFavorite()), viewModel.uiState.value)
    }

    @Test
    fun `Given a click on favorite with already as favorite it should delete the joke`() {
        val viewModel = JokeViewModel(SuccessJokeRepository())
        assertEquals(JokeUIState(), viewModel.uiState.value)
        viewModel.fetchNewJoke()
        assertEquals(JokeUIState(joke = jokeEntityNoFavorite()), viewModel.uiState.value)
        viewModel.onFavoriteClick()
        assertEquals(JokeUIState(joke = jokeEntityFavorite()), viewModel.uiState.value)
        viewModel.onFavoriteClick()
        assertEquals(JokeUIState(joke = jokeEntityNoFavorite()), viewModel.uiState.value)
    }

    @Test
    fun `Given a click on favorite with a null joke it should set an error`() {
        val viewModel = JokeViewModel(SuccessJokeRepository())

        viewModel.onFavoriteClick()
        assertEquals(JokeUIState(error = true), viewModel.uiState.value)
    }

    @Test
    fun `Given a click on favorite with an error to save favorite it should set an error`() {
        class CustomJokeRepository : JokeRepository {
            override suspend fun fetchJoke(): Result<JokeEntity> =
                Result.success(jokeEntityNoFavorite())

            override suspend fun saveJoke(joke: JokeEntity): Result<JokeEntity> =
                Result.failure(Exception())

            override suspend fun deleteJoke(joke: JokeEntity): Result<JokeEntity> =
                Result.failure(Exception())

            override suspend fun deleteJoke(jokeId: Long): Result<Unit> = Result.success(Unit)

            override fun listenAllJoke(): Flow<List<JokeEntity>> = flow {
                emit(listOf(jokeEntityFavorite()))
            }
        }
        val viewModel = JokeViewModel(CustomJokeRepository())
        assertEquals(JokeUIState(), viewModel.uiState.value)
        viewModel.fetchNewJoke()
        assertEquals(JokeUIState(joke = jokeEntityNoFavorite()), viewModel.uiState.value)
        viewModel.onFavoriteClick()
        assertEquals(JokeUIState(joke = jokeEntityNoFavorite(), error = true), viewModel.uiState.value)
    }

    @Test
    fun `Given a click on favorite with an error to delete favorite it should set an error`() {
        class CustomJokeRepository : JokeRepository {
            override suspend fun fetchJoke(): Result<JokeEntity> =
                Result.success(jokeEntityFavorite())

            override suspend fun saveJoke(joke: JokeEntity): Result<JokeEntity> =
                Result.failure(Exception())

            override suspend fun deleteJoke(joke: JokeEntity): Result<JokeEntity> =
                Result.failure(Exception())

            override suspend fun deleteJoke(jokeId: Long): Result<Unit> = Result.success(Unit)

            override fun listenAllJoke(): Flow<List<JokeEntity>> = flow {
                emit(listOf(jokeEntityFavorite()))
            }
        }
        val viewModel = JokeViewModel(CustomJokeRepository())
        assertEquals(JokeUIState(), viewModel.uiState.value)
        viewModel.fetchNewJoke()
        assertEquals(JokeUIState(joke = jokeEntityFavorite()), viewModel.uiState.value)
        viewModel.onFavoriteClick()
        assertEquals(JokeUIState(joke = jokeEntityFavorite(), error = true), viewModel.uiState.value)
    }
    //endregion
}