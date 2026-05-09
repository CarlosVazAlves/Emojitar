package com.carlosalves.emojitar.repositories.emoji

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emojis")
data class EmojiEntity(

    @PrimaryKey
    val name: String,
    val imageUrl: String
)