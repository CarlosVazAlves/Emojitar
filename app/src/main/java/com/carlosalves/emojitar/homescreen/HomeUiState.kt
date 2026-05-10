package com.carlosalves.emojitar.homescreen

import com.carlosalves.emojitar.model.Emoji

data class HomeUiState(
    val currentEmoji: Emoji? = null,
    val isLoading: Boolean = false,
    val emojiListPopulated: Boolean = false,
    val avatarsLoaded: Boolean = false
)