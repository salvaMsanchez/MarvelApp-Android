package com.example.marvelapp.presentation.characterDetail

import com.example.marvelapp.domain.models.CharacterDetail

sealed class CharacterDetailViewState {
    object Idle : CharacterDetailViewState()
    object Loading : CharacterDetailViewState()
    data class CharacterLoaded(val character: CharacterDetail) : CharacterDetailViewState()
}