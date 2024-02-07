package com.example.marvelapp.presentation.characters.subscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.marvelapp.R
import com.example.marvelapp.domain.models.Character
import com.example.marvelapp.presentation.characters.CharactersViewModel

@Composable
fun FavoriteCharactersScreen(
    charactersViewModel: CharactersViewModel
) {
    val favoriteCharacters by charactersViewModel.favoriteCharacters.collectAsState()

    if (favoriteCharacters.isNotEmpty()) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            LazyRow {
                items(favoriteCharacters.size) {
                    FavoriteCharacterItem(
                        favoriteCharacters[it],
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
                    )
                }
            }
        }
    } else {
        NoFavoriteCharacterItem()
    }

}

@Composable
fun NoFavoriteCharacterItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .background(Color.Gray), contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("No favorite characters saved yet", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Image(
                painter = painterResource(id = R.drawable.ic_sentiment_very_dissatisfied),
                modifier = Modifier.graphicsLayer {
                    scaleX = 1.6f
                    scaleY = 1.6f
                },
                contentDescription = "Favorite button"
            )
        }
    }
}

@Composable
fun FavoriteCharacterItem(character: Character, modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .height(208.dp)
        .width(125.dp)) {
        Card(
            modifier = Modifier.height(180.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(character.photo),
                contentDescription = "Favorite Character Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Text(
            text = character.name,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        )
    }
}

@Preview(heightDp = 150, widthDp = 100)
@Composable
fun FavoriteCharacterItem_Preview() {
    FavoriteCharacterItem(Character(1.toLong(), "Nombre", "Photo", true))
}