package com.example.marvelapp.domain

import com.example.marvelapp.domain.models.Character
import com.example.marvelapp.domain.models.CharacterDetail
import com.example.marvelapp.domain.models.Product
import kotlinx.coroutines.flow.Flow

interface RepositoryInterface {
    suspend fun getCharactersWithCache()
    suspend fun getCharactersWithFlow(): Flow<List<Character>>
    suspend fun getFavoriteCharactersWithFlow(): Flow<List<Character>>
    suspend fun getCharacters(limit: Int, offset: Int)
    suspend fun getCharacter(characterId: Long): CharacterDetail
    suspend fun getComics(characterId: Long): List<Product>
    suspend fun getSeries(characterId: Long): List<Product>
    suspend fun updateLocalFavoriteStatus(characterId: Long, isFavorite: Boolean)
    suspend fun deleteCharactersMinusFirstTwenty()
}