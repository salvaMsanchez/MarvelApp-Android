package com.example.marvelapp.data

import android.util.Log
import com.example.marvelapp.data.local.LocalDataSourceInterface
import com.example.marvelapp.data.local.models.CharacterLocal
import com.example.marvelapp.data.mappers.LocalToUiMapper
import com.example.marvelapp.data.mappers.RemoteToLocalMapper
import com.example.marvelapp.data.mappers.RemoteToUiMapper
import com.example.marvelapp.data.remote.RemoteDataSourceInterface
import com.example.marvelapp.data.remote.response.CharacterRemote
import com.example.marvelapp.domain.RepositoryInterface
import com.example.marvelapp.domain.models.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSourceInterface,
    private val localDataSource: LocalDataSourceInterface,
    private val remoteToLocalMapper: RemoteToLocalMapper,
    private val localToUiMapper: LocalToUiMapper,
    private val remoteToUiMapper: RemoteToUiMapper,
) : RepositoryInterface {
    override suspend fun getCharactersWithCache() {
        val localCharacters: List<CharacterLocal> = localDataSource.getCharacters()

        if (localCharacters.isNotEmpty()) {
            Log.d("SALVA", "BBDD: $localCharacters")
            val charactersWithPhoto: List<CharacterLocal> = localCharacters.filter { characterLocal ->
                !characterLocal.photo.contains("image_not_available")
            }
            localToUiMapper.mapCharacters(charactersWithPhoto)
        } else {
            val remoteCharacters: List<CharacterRemote> = remoteDataSource.getCharacters("20", "0")
            val charactersWithPhoto: List<CharacterRemote> = remoteCharacters.filter { characterRemote ->
                !characterRemote.thumbnail.path.contains("image_not_available")
            }
            localDataSource.insertCharacters(remoteToLocalMapper.mapCharacters(charactersWithPhoto))
            Log.d("SALVA", "REMOTO: $charactersWithPhoto")
            val updatedLocalCharacters: List<CharacterLocal> = localDataSource.getCharacters()
            localToUiMapper.mapCharacters(updatedLocalCharacters)
        }
    }

    override suspend fun getCharactersWithFlow(): Flow<List<Character>> {
        return localDataSource.getCharactersWithFlow().map { characterLocal ->
            characterLocal.map { Character(it.id, it.name, it.photo, it.favorite) }.sortedBy { it.name }
        }
    }

    override suspend fun getCharacters(limit: Int, offset: Int) {
        val remoteCharacters: List<CharacterRemote> =
            remoteDataSource.getCharacters(limit.toString(), offset.toString())
        val charactersWithPhoto: List<CharacterRemote> = remoteCharacters.filter { characterRemote ->
            !characterRemote.thumbnail.path.contains("image_not_available")
        }
        localDataSource.insertCharacters(remoteToLocalMapper.mapCharacters(charactersWithPhoto))
    }

    override suspend fun updateLocalFavoriteStatus(characterId: Long, isFavorite: Boolean) {
        Log.d("SALVA", "Se está actualizando el character $characterId a $isFavorite")
        localDataSource.updateFavoriteStatus(characterId, isFavorite)
    }
}