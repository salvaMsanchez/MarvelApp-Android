package com.example.marvelapp.fakes

import com.example.marvelapp.data.local.LocalDataSourceInterface
import com.example.marvelapp.data.local.models.CharacterLocal
import kotlinx.coroutines.flow.Flow

class FakeLocalDataSource : LocalDataSourceInterface {
    private val database = mutableListOf<CharacterLocal>()

    override fun getCharacters(): List<CharacterLocal> {
        return database
    }

    override fun getCharacter(characterId: Long): CharacterLocal =
        database.first()

    override fun getCharactersWithFlow(): Flow<List<CharacterLocal>> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteCharactersWithFlow(): Flow<List<CharacterLocal>> {
        TODO("Not yet implemented")
    }

    override fun insertCharacters(localCharacters: List<CharacterLocal>) {
        database.addAll(localCharacters)
    }

    override fun updateFavoriteStatus(characterId: Long, isFavorite: Boolean) {
        val character: CharacterLocal = database.first()
        val newCharacter: CharacterLocal = CharacterLocal(character.id, character.name, character.photo, character.description, !character.favorite)
        database.removeFirst()
        database.add(0, newCharacter)
    }

    override fun deleteCharactersMinusFirstTwenty() {
        if (database.size > 20) {
            database.subList(20, database.size).clear()
        }
    }
}