package com.lucienguimaraes.myjokes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.lucienguimaraes.design_system.MyJokesTheme
import com.lucienguimaraes.design_system.navigation.LeafScreen
import com.lucienguimaraes.design_system.navigation.Screen
import com.lucienguimaraes.design_system.navigation.currentTopScreenAsState
import com.lucienguimaraes.joke.addJoke
import com.lucienguimaraes.jokelist.addJokeList
import com.lucienguimaraes.myjokes.ui.AppBottomBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyJokesTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        val currentTopScreen by navController.currentTopScreenAsState()
                        AppBottomBar(
                            currentTopScreen = currentTopScreen,
                            onScreenSelected = { screen ->
                                navController.navigate(screen.route) {
                                    launchSingleTop = true
                                    restoreState = true

                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                }
                            },
                        )
                    },
                    content = { innerPadding ->
                        NavHost(
                            modifier = Modifier.padding(innerPadding),
                            navController = navController,
                            startDestination = Screen.Home.route,
                        ) {
                            navigation(
                                route = Screen.Home.route,
                                startDestination = LeafScreen.HomeJoke.createRoute(Screen.Home),
                            ) {
                                addJoke(Screen.Home)
                            }
                            navigation(
                                route = Screen.Favorite.route,
                                startDestination = LeafScreen.FavoriteList.createRoute(Screen.Favorite),
                            ) {
                                addJokeList(Screen.Favorite)
                            }
                        }
                    },
                )
            }
        }
    }
}
