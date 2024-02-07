package com.example.marvelapp.data.local

import com.example.marvelapp.data.local.models.CharacterLocal
import com.example.marvelapp.data.remote.response.CharacterRemote
import kotlinx.coroutines.flow.Flow

interface LocalDataSourceInterface {
    fun getCharacters(): List<CharacterLocal>
    fun getCharactersWithFlow(): Flow<List<CharacterLocal>>
    fun insertCharacters(localCharacters: List<CharacterLocal>)
    fun updateFavoriteStatus(characterId: Long, isFavorite: Boolean)
}