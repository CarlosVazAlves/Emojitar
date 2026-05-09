package com.carlosalves.emojitar.homescreen

import com.carlosalves.emojitar.repositories.emoji.EmojiEntity

data class HomeUiState(
    val currentEmoji: EmojiEntity? = null,
    val isLoading: Boolean = false,
    val emojiListPopulated: Boolean = false
)