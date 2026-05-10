package com.carlosalves.emojitar.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosalves.emojitar.model.Emoji
import com.carlosalves.emojitar.repositories.avatar.AvatarRepository
import com.carlosalves.emojitar.repositories.emoji.EmojiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val emojiRepository: EmojiRepository,
    private val avatarRepository: AvatarRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()
    private val emojiList: MutableList<Emoji> = mutableListOf()

    private val _events = MutableSharedFlow<HomeEvent>()
    val event = _events.asSharedFlow()

    fun loadEmojis() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                it.copy(isLoading = true)
            }

            val loadedSuccessfully = emojiRepository.tryToLoadEmojis()
            if (loadedSuccessfully) {
                emojiList.addAll(emojiRepository.getEmojis())
            }

            _uiState.update {
                it.copy(isLoading = false, emojiListPopulated = emojiList.isNotEmpty())
            }
        }
    }

    fun onRandomEmojiClick() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }

            val randomEmoji = emojiList.random()

            _uiState.update {
                it.copy(
                    currentEmoji = randomEmoji,
                    isLoading = false
                )
            }
        }
    }

    fun searchAvatar(userName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                it.copy(isLoading = true)
            }

            val loadedSuccessfully = avatarRepository.tryToLoadAvatar(userName)

            _uiState.update {
                it.copy(isLoading = false, avatarsLoaded = loadedSuccessfully)
            }

            _events.emit(if (loadedSuccessfully) HomeEvent.AvatarSaved else HomeEvent.AvatarSaveFailed)
        }
    }

    fun checkIfAnyAvatarStored() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(avatarsLoaded = avatarRepository.checkIfAnyAvatarExists())
            }
        }
    }
}