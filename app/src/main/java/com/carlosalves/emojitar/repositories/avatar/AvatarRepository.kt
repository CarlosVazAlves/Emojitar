package com.carlosalves.emojitar.repositories.avatar

import com.carlosalves.emojitar.api.ApiService
import com.carlosalves.emojitar.api.dtos.EmojiDTO
import com.carlosalves.emojitar.utilities.Converter
import javax.inject.Inject

class AvatarRepository @Inject constructor(
    private val apiService: ApiService,
    private val avatarDao: AvatarDao
) {
    suspend fun TryToLoadAvatar(username: String): Boolean {
        val hasCachedAvatar = avatarDao.exists(username)

        if (hasCachedAvatar) {
            return true
        }

        val getAvatarResponse = apiService.getAvatarsByUsername(username)

        if (getAvatarResponse.isSuccessful && getAvatarResponse.body() != null) {
            avatarDao.insert(getAvatarResponse.body()!!.toAvatarEntity())
            return true
        } else {
            return false
        }
    }

    suspend fun getAvatars() = avatarDao.getAll()

    suspend fun deleteAvatar(username: String) = avatarDao.delete(username)
}