package com.lucienguimaraes.joke

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lucienguimaraes.design_system.navigation.LeafScreen
import com.lucienguimaraes.design_system.navigation.Screen

fun NavGraphBuilder.addJoke(root: Screen) {
    composable(route = LeafScreen.HomeJoke.createRoute(root)) {

        val viewModel = hiltViewModel<JokeViewModel>()
        val uiState by viewModel.uiState

        JokeScreen(
            modifier = Modifier.fillMaxSize(),
            jokeUIState = uiState,
            onGetJokeClick = viewModel::fetchNewJoke,
            onFavoriteClick = viewModel::onFavoriteClick,
            onDismissDialog = viewModel::dismissError,
        )
    }
}
