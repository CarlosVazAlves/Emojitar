package com.carlosalves.emojitar.repositories

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carlosalves.emojitar.repositories.avatar.AvatarDao
import com.carlosalves.emojitar.repositories.avatar.AvatarEntity
import com.carlosalves.emojitar.repositories.emoji.EmojiDao
import com.carlosalves.emojitar.repositories.emoji.EmojiEntity

@Database(
    entities = [EmojiEntity::class, AvatarEntity::class],
    version = 1,
    exportSchema = false
)
abstract class EmojitarDatabase : RoomDatabase() {
    abstract fun emojiDao(): EmojiDao
    abstract fun avatarDao(): AvatarDao
}