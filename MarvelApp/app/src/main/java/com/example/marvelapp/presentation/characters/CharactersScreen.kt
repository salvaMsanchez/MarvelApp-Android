package com.example.marvelapp.presentation.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.marvelapp.presentation.characters.subscreens.CharactersLoadedScreen
import com.example.marvelapp.presentation.characters.subscreens.CharactersLoadingScreen
import com.example.marvelapp.presentation.characters.subscreens.FavoriteCharactersScreen
import com.example.marvelapp.ui.theme.BackgroundColor

@Composable
fun CharactersScreen(
    charactersViewModel: CharactersViewModel,
    state: CharactersViewState,
    onLoadMore: () -> Unit,
    onItemClicked: (Long) -> Unit
) {
    // SCREEN MANAGEMENT BY STATUS
    when (state) {
        is CharactersViewState.Idle -> {}
        is CharactersViewState.Loaded -> {
            // LOADED SCREEN
            CharactersLoadedScreen(
                charactersViewModel,
                state,
                { FavoriteCharactersScreen(charactersViewModel, onItemClicked) },
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackgroundColor),
                onLoadMore,
                onItemClicked
            ) { id, isFavorite ->
                charactersViewModel.onFavoriteClicked(id, isFavorite)
            }
        }
        is CharactersViewState.Loading -> {
            // LOADING SCREEN
            CharactersLoadingScreen()
        }
    }
}
