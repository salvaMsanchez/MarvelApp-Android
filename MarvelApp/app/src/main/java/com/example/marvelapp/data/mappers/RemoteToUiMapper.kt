package com.example.marvelapp.data.mappers

import com.example.marvelapp.data.remote.response.CharactersRemote
import com.example.marvelapp.domain.models.Characters
import javax.inject.Inject

class RemoteToUiMapper @Inject constructor() {
    fun map(charactersRemote: List<CharactersRemote>): List<Characters> = charactersRemote.map {
        Characters(
            it.id,
            it.name,
            it.thumbnail.path + "." + it.thumbnail.extension,
            false,
        )
    }
}