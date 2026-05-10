package com.carlosalves.emojitar.repositories.avatar

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.carlosalves.emojitar.model.Avatar

@Entity(tableName = "avatars")
data class AvatarEntity(

    @PrimaryKey
    val username: String,
    val id: Int,
    val avatarUrl: String
) {
    fun toAvatar() = Avatar(username, id, avatarUrl)
}