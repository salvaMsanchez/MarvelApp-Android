package com.example.marvelapp.data.remote

import com.example.marvelapp.data.remote.response.CharactersRemote
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val marvelApi: MarvelApi) :
    RemoteDataSourceInterface {
    override suspend fun getCharacters(): List<CharactersRemote> =
        marvelApi.getCharacters().data.results
}