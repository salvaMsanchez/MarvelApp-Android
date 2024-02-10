package com.example.marvelapp.data.mappers

import com.example.marvelapp.data.local.models.CharacterLocal
import com.example.marvelapp.domain.models.Character
import com.example.marvelapp.domain.models.CharacterDetail
import javax.inject.Inject

class LocalToUiMapper @Inject constructor() {
    fun mapCharacters(charactersLocal: List<CharacterLocal>): List<Character> =
        charactersLocal.map {
            Character(
                it.id,
                it.name,
                it.photo,
                false,
            )
        }

    fun mapCharacterDetail(characterLocal: CharacterLocal): CharacterDetail =
        CharacterDetail(
            characterLocal.id,
            characterLocal.name,
            characterLocal.description,
            characterLocal.photo,
        )
}