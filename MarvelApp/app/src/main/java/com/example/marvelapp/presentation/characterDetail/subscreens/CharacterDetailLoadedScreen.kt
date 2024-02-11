package com.example.marvelapp.presentation.characterDetail.subscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.marvelapp.domain.models.Product
import com.example.marvelapp.presentation.characterDetail.CharacterDetailViewState
import com.example.marvelapp.presentation.characterDetail.components.DescriptionDetail
import com.example.marvelapp.presentation.characterDetail.components.HeaderDetail
import com.example.marvelapp.presentation.characterDetail.components.ProductsDetail

@Composable
fun CharacterDetailLoadedScreen(
    state: CharacterDetailViewState.CharacterLoaded,
    comics: List<Product>,
    series: List<Product>,
) {
    LazyColumn(modifier = Modifier.background(Color.Black.copy(alpha = 0.85f))) {
        item {
            HeaderDetail(state.character.name, state.character.photo, modifier = Modifier.padding(bottom = 8.dp))
        }
        item {
            DescriptionDetail(
                state.character.description,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )
        }
        item {
            ProductsDetail(
                "COMICS",
                comics,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
            )
        }
        item {
            ProductsDetail(
                "SERIES",
                series,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
            )
        }
    }
}