package com.carlosalves.emojitar.avatarlistscreen

import com.carlosalves.emojitar.model.Avatar

data class AvatarListUiState(
    val avatars: List<Avatar> = emptyList()
)