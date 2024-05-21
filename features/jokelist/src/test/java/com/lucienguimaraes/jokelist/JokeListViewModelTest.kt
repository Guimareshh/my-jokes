package com.lucienguimaraes.jokelist

import app.cash.turbine.test
import com.lucienguimaraes.datasource.dataTest.ErrorJokeRepository
import com.lucienguimaraes.datasource.dataTest.SuccessJokeRepository
import com.lucienguimaraes.datasource.entities.jokeEntityFavorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

internal class JokeListViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `Given a flow of list of joke it should be map to an ui state`() = runTest {
        val viewModel = JokeListViewModel(SuccessJokeRepository())
        viewModel.uiState.test {
            assertEquals(JokeListUIState(listOf(jokeEntityFavorite())), awaitItem())
        }
    }

    @Test
    fun `Given a flow of empty list of joke it should be map to an ui state`() = runTest {
        val viewModel = JokeListViewModel(ErrorJokeRepository())
        viewModel.uiState.test {
            assertEquals(JokeListUIState(), awaitItem())
        }
    }
}