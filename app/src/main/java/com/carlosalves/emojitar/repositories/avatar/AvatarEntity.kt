package com.carlosalves.emojitar.repositories.avatar

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "avatars")
data class AvatarEntity(

    @PrimaryKey
    val username: String,
    val id: Int,
    val avatarUrl: String
)