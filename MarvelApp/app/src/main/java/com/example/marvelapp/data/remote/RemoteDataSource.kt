package com.example.marvelapp.data.remote

import com.example.marvelapp.data.remote.response.CharacterRemote
import com.example.marvelapp.data.remote.response.ProductRemote
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val marvelApi: MarvelApi) :
    RemoteDataSourceInterface {
    override suspend fun getCharacters(limit: String, offset: String): List<CharacterRemote> =
        marvelApi.getCharacters(limit, offset).data.results

    override suspend fun getComics(characterId: Long): List<ProductRemote> =
        marvelApi.getComics(characterId).data.results

    override suspend fun getSeries(characterId: Long): List<ProductRemote> =
        marvelApi.getSeries(characterId).data.results
}