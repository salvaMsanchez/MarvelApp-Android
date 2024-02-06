package com.example.marvelapp.data.mappers

import com.example.marvelapp.data.local.models.CharacterLocal
import com.example.marvelapp.domain.models.Character
import javax.inject.Inject

class LocalToUiMapper @Inject constructor() {
    fun mapCharacters(charactersLocal: List<CharacterLocal>): List<Character> = charactersLocal.map {
        Character(
            it.id,
            it.name,
            it.photo,
            false,
        )
    }
}