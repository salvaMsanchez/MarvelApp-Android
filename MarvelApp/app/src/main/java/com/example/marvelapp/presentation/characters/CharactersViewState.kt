package com.example.marvelapp.presentation.characters

import com.example.marvelapp.domain.models.Character

sealed class CharactersViewState {
    object Idle : CharactersViewState()
    data class Loaded(val data: List<Character>, val loadingMore: Boolean) : CharactersViewState()
    object Loading : CharactersViewState()
}