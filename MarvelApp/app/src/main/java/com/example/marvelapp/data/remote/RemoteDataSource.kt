package com.example.marvelapp.data.remote

import com.example.marvelapp.data.remote.response.CharacterRemote
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val marvelApi: MarvelApi) :
    RemoteDataSourceInterface {
    override suspend fun getCharacters(limit: String, offset: String): List<CharacterRemote> =
        marvelApi.getCharacters(limit, offset).data.results
}