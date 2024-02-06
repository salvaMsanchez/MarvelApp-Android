package com.example.marvelapp.presentation.characters.subscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.marvelapp.domain.models.Character
import com.example.marvelapp.presentation.characters.CharactersViewModel
import com.example.marvelapp.presentation.characters.CharactersViewState
import com.example.marvelapp.presentation.characters.generateCharacters

@Composable
fun FavoriteCharactersScreen(charactersViewModel: CharactersViewModel) {
    val favoriteCharacters by charactersViewModel.favoriteCharacters.collectAsState()
    if (favoriteCharacters.isNotEmpty()) {
        LazyRow {
            items(favoriteCharacters.size) {
                FavoriteCharacterItem(favoriteCharacters[it])
            }
        }
    } else {
        NoFavoriteCharacterItem()
    }

}

@Composable
fun NoFavoriteCharacterItem() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .background(Color.Gray), contentAlignment = Alignment.Center) {
        Text("No Favorite Characters saved yet")
    }
}

@Composable
fun FavoriteCharacterItem(character: Character, modifier: Modifier = Modifier) {
    Column {
        ElevatedCard(
            modifier = modifier.height(150.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(character.photo),
                contentDescription = "Favorite Character Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
        }
        Text(
            text = character.name,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun FavoriteCharacterItem_Preview() {
    FavoriteCharacterItem(Character(1.toLong(), "Noombre", "Photo", true))
}