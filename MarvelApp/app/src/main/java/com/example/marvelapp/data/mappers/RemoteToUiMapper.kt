package com.example.marvelapp.data.mappers

import com.example.marvelapp.domain.models.Character
import com.example.marvelapp.data.remote.response.CharacterRemote
import javax.inject.Inject

class RemoteToUiMapper @Inject constructor() {
    fun mapCharacters(charactersRemote: List<CharacterRemote>): List<Character> = charactersRemote.map {
        Character(
            it.id,
            it.name,
            "${it.thumbnail.path}.${it.thumbnail.extension}",
            false,
        )
    }
}