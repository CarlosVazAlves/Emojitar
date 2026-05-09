package com.carlosalves.emojitar.api.dtos

import com.carlosalves.emojitar.repositories.avatar.AvatarEntity

data class AvatarDTO(
    val login: String,
    val id: Int,
    val avatarUrl: String
) {
    fun toAvatarEntity() = AvatarEntity(login, id, avatarUrl)
}