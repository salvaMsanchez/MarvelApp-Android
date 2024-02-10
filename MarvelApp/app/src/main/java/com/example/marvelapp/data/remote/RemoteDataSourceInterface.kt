package com.example.marvelapp.data.remote

import com.example.marvelapp.data.remote.response.CharacterRemote
import com.example.marvelapp.data.remote.response.ComicRemote

interface RemoteDataSourceInterface {
    suspend fun getCharacters(limit: String, offset: String): List<CharacterRemote>
    suspend fun getComics(characterId: String): List<ComicRemote>
}