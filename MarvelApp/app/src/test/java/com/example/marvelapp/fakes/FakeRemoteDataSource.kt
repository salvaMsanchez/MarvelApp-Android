package com.example.marvelapp.fakes

import com.example.marvelapp.data.remote.RemoteDataSourceInterface
import com.example.marvelapp.data.remote.response.CharacterRemote
import com.example.marvelapp.data.remote.response.ProductRemote
import com.example.marvelapp.utils.generateRemoteCharacters
import com.example.marvelapp.utils.generateRemoteProducts

class FakeRemoteDataSource : RemoteDataSourceInterface {
    override suspend fun getCharacters(limit: String, offset: String): List<CharacterRemote> =
        generateRemoteCharacters()

    override suspend fun getComics(characterId: Long): List<ProductRemote> =
        generateRemoteProducts()

    override suspend fun getSeries(characterId: Long): List<ProductRemote> =
        generateRemoteProducts()
}