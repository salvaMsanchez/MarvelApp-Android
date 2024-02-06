package com.example.marvelapp.di

import android.content.Context
import androidx.room.Room
import com.example.marvelapp.data.local.LocalDataSource
import com.example.marvelapp.data.local.LocalDataSourceInterface
import com.example.marvelapp.data.local.database.CharacterDAO
import com.example.marvelapp.data.local.database.CharacterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    fun providesCharacterDatabase(@ApplicationContext context: Context): CharacterDatabase {
        return Room.databaseBuilder(
            context,
            CharacterDatabase::class.java, "characters-database"
        ).build()
    }

    @Provides
    fun providesCharacterDao(database: CharacterDatabase): CharacterDAO {
        return database.characterDAO()
    }

    @Provides
    fun providesLocalDataSourceInterface(localDataSource: LocalDataSource): LocalDataSourceInterface {
        return localDataSource
    }
}