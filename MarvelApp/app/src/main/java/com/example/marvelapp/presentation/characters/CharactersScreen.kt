package com.example.marvelapp.presentation.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.marvelapp.presentation.characters.subscreens.CharactersLoadedScreen
import com.example.marvelapp.presentation.characters.subscreens.FavoriteCharactersScreen
import com.example.marvelapp.ui.theme.BackgroundColor
import com.example.marvelapp.ui.theme.MarvelColor

@Composable
fun CharactersScreen(
    charactersViewModel: CharactersViewModel,
    state: CharactersViewState,
    onLoadMore: () -> Unit,
    onItemClicked: (Long) -> Unit
) {
    when (state) {
        is CharactersViewState.Idle -> {}
        is CharactersViewState.Loaded -> {
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
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = MarvelColor)
            }
        }
    }
}
