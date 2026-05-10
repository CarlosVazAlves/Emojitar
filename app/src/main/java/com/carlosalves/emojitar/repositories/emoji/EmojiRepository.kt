package com.carlosalves.emojitar.repositories.emoji

import com.carlosalves.emojitar.api.ApiService
import com.carlosalves.emojitar.api.dtos.EmojiDTO
import com.carlosalves.emojitar.utilities.Converter
import javax.inject.Inject

class EmojiRepository @Inject constructor(
    private val apiService: ApiService,
    private val emojiDao: EmojiDao,
) {
    suspend fun tryToLoadEmojis(): Boolean {

        val hasCachedEmojis = emojiDao.hasAny()

        if (hasCachedEmojis) {
            return true
        }

        try {
            val emojisMap = apiService.getEmojis()
            val listEmojis = Converter.convertMapToEmojiList(emojisMap)
            emojiDao.insertAll(listEmojis.map(EmojiDTO::toEmojiEntity))
            return true
        } catch (_: Exception) {
            return false
        }
    }

    suspend fun getEmojis() = emojiDao.getAll().map(EmojiEntity::toEmoji)

    suspend fun deleteAllEmojis() = emojiDao.deleteAll()
}