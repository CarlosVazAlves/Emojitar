package com.carlosalves.emojitar.utilities

import com.carlosalves.emojitar.api.dtos.EmojiDTO

class Converter {

    companion object {
        fun convertMapToEmojiList(emojiMap: Map<String, String>): List<EmojiDTO> {
            val emojiDTOList = mutableListOf<EmojiDTO>()
            emojiMap.keys.forEach { key -> emojiDTOList.add(EmojiDTO(key, emojiMap[key]!!)) }
            return emojiDTOList.toList()
        }
    }
}