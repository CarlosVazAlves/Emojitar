package com.carlosalves.emojitar.repositories.avatar

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AvatarDao {

    @Query("SELECT * FROM avatars")
    suspend fun getAll(): List<AvatarEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM avatars LIMIT 1)")
    suspend fun hasAny(): Boolean

    @Query("SELECT * FROM avatars WHERE username = :username")
    suspend fun get(username: String): AvatarEntity?

    @Query("SELECT EXISTS(SELECT 1 FROM emojis WHERE name = :username)")
    suspend fun exists(username: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(avatar: AvatarEntity)

    @Query("DELETE FROM avatars WHERE username = :username")
    suspend fun delete(username: String)

    @Query("DELETE FROM avatars")
    suspend fun deleteAll()
}