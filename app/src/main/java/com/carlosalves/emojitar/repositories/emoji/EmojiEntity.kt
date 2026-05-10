package com.carlosalves.emojitar.repositories.emoji

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.carlosalves.emojitar.model.Emoji

@Entity(tableName = "emojis")
data class EmojiEntity(

    @PrimaryKey
    val name: String,
    val imageUrl: String
) {
    fun toEmoji() = Emoji(name, imageUrl)
}