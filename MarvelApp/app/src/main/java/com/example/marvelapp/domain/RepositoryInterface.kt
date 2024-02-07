package com.example.marvelapp.domain

import com.example.marvelapp.domain.models.Character
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {
    suspend fun getCharactersWithCache()
    suspend fun getCharactersWithFlow(): Flow<List<Character>>
    suspend fun getCharacters(limit: Int, offset: Int)
    suspend fun updateLocalFavoriteStatus(characterId: Long, isFavorite: Boolean)
}