package com.example.marvelapp.fakes

import com.example.marvelapp.data.local.LocalDataSourceInterface
import com.example.marvelapp.data.local.models.CharacterLocal
import com.example.marvelapp.utils.generateLocalCharacters
import kotlinx.coroutines.flow.Flow

class FakeLocalDataSource : LocalDataSourceInterface {
    private var firstCall = true

    override fun getCharacters(): List<CharacterLocal> {
        return if (firstCall) {
            firstCall = false
            emptyList()
        } else {
            generateLocalCharacters()
        }
    }

    override fun getCharacter(characterId: Long): CharacterLocal =
        CharacterLocal(1009351, "Hulk", "Description", "Photo", true)

    override fun getCharactersWithFlow(): Flow<List<CharacterLocal>> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteCharactersWithFlow(): Flow<List<CharacterLocal>> {
        TODO("Not yet implemented")
    }

    override fun insertCharacters(localCharacters: List<CharacterLocal>) { }

    override fun updateFavoriteStatus(characterId: Long, isFavorite: Boolean) { }

    override fun deleteCharactersMinusFirstTwenty() { }
}