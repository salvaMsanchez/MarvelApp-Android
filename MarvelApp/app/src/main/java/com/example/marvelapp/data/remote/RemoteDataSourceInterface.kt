package com.example.marvelapp.data.remote

import com.example.marvelapp.data.remote.response.CharactersRemote

interface RemoteDataSourceInterface {
    suspend fun getCharacters(limit: String, offset: String): List<CharactersRemote>
}