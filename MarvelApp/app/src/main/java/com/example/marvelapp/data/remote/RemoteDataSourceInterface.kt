package com.example.marvelapp.data.remote

import com.example.marvelapp.data.remote.response.CharacterRemote
import com.example.marvelapp.data.remote.response.ProductRemote

interface RemoteDataSourceInterface {
    suspend fun getCharacters(limit: String, offset: String): List<CharacterRemote>
    suspend fun getComics(characterId: Long): List<ProductRemote>
    suspend fun getSeries(characterId: Long): List<ProductRemote>
}