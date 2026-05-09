package com.carlosalves.emojitar.repositories.emoji

import com.carlosalves.emojitar.api.ApiService
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

        val getEmojisResponse = apiService.getEmojis()

        if (getEmojisResponse.isSuccessful && getEmojisResponse.body() != null) {
            val listEmojis = Converter.convertMapToEmojiList(getEmojisResponse.body()!!)
            emojiDao.insertAll(listEmojis.map { emojiDTO -> emojiDTO.toEmojiEntity() })
            return true
        } else {
            return false
        }
    }

    suspend fun getEmojis() = emojiDao.getAll()

    suspend fun deleteAllEmojis() = emojiDao.deleteAll()
}