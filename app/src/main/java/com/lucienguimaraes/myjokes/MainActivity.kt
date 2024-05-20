package com.lucienguimaraes.myjokes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.lucienguimaraes.design_system.MyJokesTheme
import com.lucienguimaraes.myjokes.ui.AppBottomBar
import com.lucienguimaraes.myjokes.ui.LeafScreen
import com.lucienguimaraes.myjokes.ui.Screen
import com.lucienguimaraes.myjokes.ui.currentTopScreenAsState

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
                                composable(LeafScreen.HomeJoke.createRoute(Screen.Home)) {
                                    Surface(modifier = Modifier.fillMaxSize()) {
                                        Text(text = LeafScreen.HomeJoke.toString())
                                    }
                                }
                            }
                            navigation(
                                route = Screen.Favorite.route,
                                startDestination = LeafScreen.FavoriteList.createRoute(Screen.Favorite),
                            ) {
                                composable(LeafScreen.FavoriteList.createRoute(Screen.Favorite)) {
                                    Surface(modifier = Modifier.fillMaxSize()) {
                                        Text(text = LeafScreen.FavoriteList.toString())
                                    }
                                }
                            }
                        }
                    },
                )
            }
        }
    }
}
