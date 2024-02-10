package com.example.marvelapp.presentation.characterDetail

import com.example.marvelapp.domain.models.Comic

sealed class CharacterDetailViewState {
    object Idle : CharacterDetailViewState()
    data class ComicsLoading(val loading: Boolean) : CharacterDetailViewState()
    data class SeriesLoading(val loading: Boolean) : CharacterDetailViewState()
    //data class ComicsLoaded(val comics: List<Comic>) : CharacterDetailViewState()
}