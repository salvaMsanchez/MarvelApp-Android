package com.example.marvelapp.presentation.characters

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.marvelapp.domain.models.Character
import com.example.marvelapp.presentation.characters.subscreens.CharactersLoadedScreen
import com.example.marvelapp.presentation.characters.subscreens.FavoriteCharactersScreen

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
            Column(modifier = Modifier.fillMaxSize()) {
                FavoriteCharactersScreen(charactersViewModel)
                CharactersLoadedScreen(state, onLoadMore, onItemClicked)
            }
        }

        is CharactersViewState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Red)
            }
        }
    }
}

fun generateCharacters() =
    (0 until 50).map { Character(it.toLong(), "Nombre $it", "Photo $it", false) }
