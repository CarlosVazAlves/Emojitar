package com.carlosalves.emojitar.emojilistscreen

import com.carlosalves.emojitar.model.Emoji

data class EmojiListUiState(
    val emojis: List<Emoji> = emptyList(),
    val isRefreshing: Boolean = false
)