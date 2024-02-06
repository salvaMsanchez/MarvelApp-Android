package com.example.marvelapp.domain

import com.example.marvelapp.domain.models.Character

interface RepositoryInterface {
    suspend fun getCharactersWithCache(): List<Character>
    suspend fun getCharacters(limit: Int, offset: Int): List<Character>
}