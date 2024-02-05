package com.example.marvelapp.data.remote

import com.example.marvelapp.data.remote.response.CharactersRemote
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val marvelApi: MarvelApi) :
    RemoteDataSourceInterface {
    override suspend fun getCharacters(limit: String, offset: String): List<CharactersRemote> =
        marvelApi.getCharacters(limit, offset).data.results
}