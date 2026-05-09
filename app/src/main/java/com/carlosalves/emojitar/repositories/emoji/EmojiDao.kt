package com.carlosalves.emojitar.repositories.emoji

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmojiDao {

    @Query("SELECT EXISTS(SELECT 1 FROM emojis LIMIT 1)")
    suspend fun hasAny(): Boolean
    @Query("SELECT * FROM emojis")
    suspend fun getAll(): List<EmojiEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(emojis: List<EmojiEntity>)

    @Query("DELETE FROM emojis")
    suspend fun deleteAll()
}