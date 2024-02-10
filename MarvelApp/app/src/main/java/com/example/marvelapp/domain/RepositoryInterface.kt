package com.example.marvelapp.domain

import com.example.marvelapp.data.local.models.CharacterLocal
import com.example.marvelapp.domain.models.Character
import com.example.marvelapp.domain.models.Comic
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {
    suspend fun getCharactersWithCache()
    suspend fun getCharactersWithFlow(): Flow<List<Character>>
    suspend fun getFavoriteCharactersWithFlow(): Flow<List<Character>>
    suspend fun getCharacters(limit: Int, offset: Int)
    suspend fun getComics(characterId: String): List<Comic>
    suspend fun updateLocalFavoriteStatus(characterId: Long, isFavorite: Boolean)
}