package com.carlosalves.emojitar.avatarlistscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlosalves.emojitar.model.Avatar
import com.carlosalves.emojitar.repositories.avatar.AvatarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvatarListViewModel @Inject constructor(
    private val repository: AvatarRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AvatarListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadAvatars()
    }

    private fun loadAvatars() {
        viewModelScope.launch {
            val avatars = repository.getAvatars()
            _uiState.update {
                it.copy(
                    avatars = avatars
                )
            }
        }
    }

    fun deleteAvatar(avatar: Avatar) {
        viewModelScope.launch {
            repository.deleteAvatar(avatar.username)
            _uiState.update { state ->
                state.copy(
                    avatars = state.avatars - avatar
                )
            }
        }
    }
}