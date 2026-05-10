package com.carlosalves.emojitar.repositories.avatar

import com.carlosalves.emojitar.api.ApiService
import javax.inject.Inject

class AvatarRepository @Inject constructor(
    private val apiService: ApiService,
    private val avatarDao: AvatarDao
) {
    suspend fun tryToLoadAvatar(username: String): Boolean {
        val hasCachedAvatar = avatarDao.exists(username)

        if (hasCachedAvatar) {
            return true
        }

        try {
            val avatar = apiService.getAvatarsByUsername(username)
            avatarDao.insert(avatar.toAvatarEntity())
            return true
        } catch (_: Exception) {
            return false
        }
    }

    suspend fun getAvatars() = avatarDao.getAll().map(AvatarEntity::toAvatar)

    suspend fun deleteAvatar(username: String) = avatarDao.delete(username)

    suspend fun checkIfAnyAvatarExists() = avatarDao.hasAny()
}