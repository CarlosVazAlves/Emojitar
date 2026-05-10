package com.carlosalves.emojitar.api.dtos

import com.carlosalves.emojitar.repositories.avatar.AvatarEntity
import com.google.gson.annotations.SerializedName

data class AvatarDTO(
    @SerializedName("login")
    val userName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String
) {
    fun toAvatarEntity() = AvatarEntity(userName, id, avatarUrl)
}