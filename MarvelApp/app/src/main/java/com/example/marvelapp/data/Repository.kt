package com.example.marvelapp.data

import com.example.marvelapp.data.mappers.RemoteToUiMapper
import com.example.marvelapp.data.remote.RemoteDataSourceInterface
import com.example.marvelapp.data.remote.response.CharactersRemote
import com.example.marvelapp.domain.RepositoryInterface
import com.example.marvelapp.domain.models.Characters
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSourceInterface,
    private val remoteToUiMapper: RemoteToUiMapper,
) : RepositoryInterface {
    override suspend fun getCharacters(limit: Int, offset: Int): List<Characters> {
        val remoteCharacters: List<CharactersRemote> = remoteDataSource.getCharacters(limit.toString(), offset.toString())
        return remoteToUiMapper.map(remoteCharacters)
    }
}