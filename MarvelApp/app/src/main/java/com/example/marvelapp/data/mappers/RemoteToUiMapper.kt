package com.example.marvelapp.data.mappers

import com.example.marvelapp.domain.models.Character
import com.example.marvelapp.data.remote.response.CharacterRemote
import com.example.marvelapp.data.remote.response.ComicRemote
import com.example.marvelapp.domain.models.Comic
import javax.inject.Inject

class RemoteToUiMapper @Inject constructor() {
    fun mapComics(comicsRemote: List<ComicRemote>): List<Comic> = comicsRemote.map {
        Comic(
            it.id,
            it.title,
            "${it.thumbnail.path}.${it.thumbnail.extension}",
        )
    }
}