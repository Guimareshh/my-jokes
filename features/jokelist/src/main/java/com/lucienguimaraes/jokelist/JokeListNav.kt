package com.lucienguimaraes.jokelist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lucienguimaraes.design_system.navigation.LeafScreen
import com.lucienguimaraes.design_system.navigation.Screen

fun NavGraphBuilder.addJokeList(root: Screen) {
    composable(route = LeafScreen.FavoriteList.createRoute(root)) {

        val viewModel = hiltViewModel<JokeListViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        JokeListScreen(
            modifier = Modifier.fillMaxSize(),
            jokeListUIState = uiState,
            onDeleteClick = viewModel::onDeleteJoke,
        )
    }
}
