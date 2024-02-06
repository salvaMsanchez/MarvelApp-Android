package com.example.marvelapp.data

import com.example.marvelapp.data.local.LocalDataSourceInterface
import com.example.marvelapp.data.local.models.CharacterLocal
import com.example.marvelapp.data.mappers.LocalToUiMapper
import com.example.marvelapp.data.mappers.RemoteToLocalMapper
import com.example.marvelapp.data.mappers.RemoteToUiMapper
import com.example.marvelapp.data.remote.RemoteDataSourceInterface
import com.example.marvelapp.data.remote.response.CharacterRemote
import com.example.marvelapp.domain.RepositoryInterface
import com.example.marvelapp.domain.models.Character
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSourceInterface,
    private val localDataSource: LocalDataSourceInterface,
    private val remoteToLocalMapper: RemoteToLocalMapper,
    private val localToUiMapper: LocalToUiMapper,
    private val remoteToUiMapper: RemoteToUiMapper,
) : RepositoryInterface {
    override suspend fun getCharactersWithCache(): List<Character> {
        val localCharacters: List<CharacterLocal> = localDataSource.getCharacters()

        return if (localCharacters.isNotEmpty()) {
            localToUiMapper.mapCharacters(localCharacters)
        } else {
            val remoteCharacters: List<CharacterRemote> = remoteDataSource.getCharacters("20", "0")
            localDataSource.insertCharacters(remoteToLocalMapper.mapCharacters(remoteCharacters))

            val updatedLocalCharacters: List<CharacterLocal> = localDataSource.getCharacters()
            localToUiMapper.mapCharacters(updatedLocalCharacters)
        }
    }

    override suspend fun getCharacters(limit: Int, offset: Int): List<Character> {
        val remoteCharacters: List<CharacterRemote> =
            remoteDataSource.getCharacters(limit.toString(), offset.toString())
        return remoteToUiMapper.mapCharacters(remoteCharacters)
    }
}