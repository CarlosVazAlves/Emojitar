package com.carlosalves.emojitar.api.dtos

import com.carlosalves.emojitar.repositories.emoji.EmojiEntity

data class EmojiDTO(
    val name: String,
    val url: String
) {
    fun toEmojiEntity() = EmojiEntity(name, url)
}