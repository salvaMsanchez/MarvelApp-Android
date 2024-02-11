package com.example.marvelapp.presentation.characters

sealed class CharactersViewState {
    object Idle : CharactersViewState()
    data class Loaded(val loadingMore: Boolean) : CharactersViewState()
    object Loading : CharactersViewState()
}