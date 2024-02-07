package com.example.marvelapp.data.local

import com.example.marvelapp.data.local.database.CharacterDAO
import com.example.marvelapp.data.local.models.CharacterLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: CharacterDAO
) : LocalDataSourceInterface {
    override fun getCharacters(): List<CharacterLocal> = dao.getCharacters()
    override fun getCharactersWithFlow(): Flow<List<CharacterLocal>> = dao.getCharactersWithFlow()
    override fun getFavoriteCharactersWithFlow(): Flow<List<CharacterLocal>> = dao.getFavoriteCharactersWithFlow()
    override fun insertCharacters(localCharacters: List<CharacterLocal>) = dao.insertCharacters(localCharacters)
    override fun updateFavoriteStatus(characterId: Long, isFavorite: Boolean) {
        return dao.updateFavoriteStatus(characterId, isFavorite)
    }
}