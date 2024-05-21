package com.lucienguimaraes.joke

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lucienguimaraes.design_system.navigation.LeafScreen
import com.lucienguimaraes.design_system.navigation.Screen

fun NavGraphBuilder.addJoke(root: Screen) {
    composable(route = LeafScreen.HomeJoke.createRoute(root)) {
        JokeScreen(Modifier.fillMaxSize())
    }
}
