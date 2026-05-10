package com.carlosalves.emojitar.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.carlosalves.emojitar.emojilistscreen.EmojiListScreen
import com.carlosalves.emojitar.emojilistscreen.EmojiListViewModel

@Composable
fun EmojiListRoute(
    viewModel: EmojiListViewModel = hiltViewModel()
) {

    val uiState by viewModel
        .uiState
        .collectAsStateWithLifecycle()

    EmojiListScreen(
        uiState = uiState,
        onEmojiClick = viewModel::removeEmoji,
        onRefresh = viewModel::refresh
    )
}