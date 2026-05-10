package com.carlosalves.emojitar.repositories

import android.content.Context
import androidx.room.Room
import com.carlosalves.emojitar.repositories.avatar.AvatarDao
import com.carlosalves.emojitar.repositories.emoji.EmojiDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): EmojitarDatabase {
        return Room.databaseBuilder(
            context,
            EmojitarDatabase::class.java,
            "emojitar_database"
        ).build()
    }

    @Provides
    fun provideEmojiDao(
        database: EmojitarDatabase
    ): EmojiDao {
        return database.emojiDao()
    }

    @Provides
    fun provideAvatarDao(
        database: EmojitarDatabase
    ): AvatarDao {
        return database.avatarDao()
    }
}