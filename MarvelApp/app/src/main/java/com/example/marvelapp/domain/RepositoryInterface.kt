package com.example.marvelapp.domain

import com.example.marvelapp.domain.models.Characters

interface RepositoryInterface {
    suspend fun getCharacters(): List<Characters>
}