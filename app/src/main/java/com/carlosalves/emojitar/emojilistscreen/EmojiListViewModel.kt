package com.carlosalves.emojitar.emojilistscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosalves.emojitar.model.Emoji
import com.carlosalves.emojitar.repositories.emoji.EmojiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmojiListViewModel @Inject constructor(
    private val repository: EmojiRepository
) : ViewModel() {
    private var originalList = emptyList<Emoji>()
    private val _uiState = MutableStateFlow(EmojiListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadEmojis()
    }

    private fun loadEmojis() {
        viewModelScope.launch {
            val emojis = repository.getEmojis()
            originalList = emojis
            _uiState.update {
                it.copy(
                    emojis = emojis
                )
            }
        }
    }

    fun removeEmoji(emoji: Emoji) {
        _uiState.update {
            it.copy(
                emojis = it.emojis - emoji
            )
        }
    }

    fun refresh() {
        _uiState.update {
            it.copy(isRefreshing = true)
        }
        _uiState.update {
            it.copy(
                emojis = originalList,
                isRefreshing = false
            )
        }
    }
}