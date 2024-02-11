package com.example.marvelapp.presentation.characterDetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.marvelapp.presentation.characterDetail.subscreens.CharacterDetailLoadedScreen
import com.example.marvelapp.presentation.characterDetail.subscreens.CharacterDetailLoadingScreen

@Composable
fun CharacterDetailScreen(
    characterDetailViewModel: CharacterDetailViewModel,
    state: CharacterDetailViewState,
    onViewAppear: () -> Unit,
    onCharacterLoaded: () -> Unit
) {
    // OBSERVERS
    val comics by characterDetailViewModel.comics.collectAsState()
    val series by characterDetailViewModel.series.collectAsState()

    // CALLBACK
    onViewAppear()

    // SCREEN MANAGEMENT BY STATUS
    when (state) {
        is CharacterDetailViewState.Idle -> {}
        is CharacterDetailViewState.Loading -> {
            // LOADING SCREEN
            CharacterDetailLoadingScreen()
        }
        is CharacterDetailViewState.CharacterLoaded -> {
            // CALLBACK
            onCharacterLoaded()
            // LOADED SCREEN
            CharacterDetailLoadedScreen(state = state, comics = comics, series = series)
        }
    }
}
