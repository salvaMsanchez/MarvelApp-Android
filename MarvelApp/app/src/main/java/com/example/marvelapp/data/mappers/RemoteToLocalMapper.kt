package com.example.marvelapp.data.mappers

import com.example.marvelapp.data.local.models.CharacterLocal
import com.example.marvelapp.data.remote.response.CharacterRemote
import javax.inject.Inject

class RemoteToLocalMapper @Inject constructor() {
    fun mapCharacters(charactersRemote: List<CharacterRemote>): List<CharacterLocal> = charactersRemote.map {
        CharacterLocal(
            it.id,
            it.name,
            it.description,
            it.thumbnail.path + "." + it.thumbnail.extension,
            false,
        )
    }
}