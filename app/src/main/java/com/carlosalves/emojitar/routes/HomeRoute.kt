package com.carlosalves.emojitar.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.carlosalves.emojitar.homescreen.HomeScreen
import com.carlosalves.emojitar.homescreen.HomeViewModel

@Composable
fun HomeRoute(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel
        .uiState
        .collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onRandomEmojiClick = viewModel::onRandomEmojiClick,
        onGetEmojiClick = viewModel::loadEmojis,
        onEmojiListClick = {
            navController.navigate("emoji_list")
        }
    )
}