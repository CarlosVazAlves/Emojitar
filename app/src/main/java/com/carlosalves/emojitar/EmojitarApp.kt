package com.carlosalves.emojitar

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dagger.hilt.android.HiltAndroidApp
import androidx.navigation.compose.rememberNavController
import com.carlosalves.emojitar.homescreen.HomeScreen
import com.carlosalves.emojitar.homescreen.HomeViewModel

@HiltAndroidApp
class EmojitarApplication : Application()

@Composable
fun EmojitarApp() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeRoute()
            /*HomeScreen(
                onEmojiListClick = {
                    navController.navigate("emoji_list")
                }
            )*/
        }

    }
}

@Composable
fun HomeRoute(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel
        .uiState
        .collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onRandomEmojiClick = viewModel::onRandomEmojiClick,
        onGetEmojiClick = viewModel::loadEmojis
    )
}