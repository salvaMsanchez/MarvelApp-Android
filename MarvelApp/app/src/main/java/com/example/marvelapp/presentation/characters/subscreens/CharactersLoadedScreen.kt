package com.example.marvelapp.presentation.characters.subscreens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.example.marvelapp.domain.models.Character
import com.example.marvelapp.presentation.characters.CharactersViewModel
import com.example.marvelapp.presentation.characters.CharactersViewState

@Composable
fun CharactersLoadedScreen(
    charactersViewModel: CharactersViewModel,
    state: CharactersViewState.Loaded,
    onLoadMore: () -> Unit,
    onItemClicked: (Long) -> Unit,
    onFavoriteCheckedChange: (Long, Boolean) -> Unit
) {
    val characters by charactersViewModel.characters.collectAsState()

    val listState = rememberLazyListState()

    val layoutInfo = remember { derivedStateOf { listState.layoutInfo } }

    LaunchedEffect(layoutInfo.value.visibleItemsInfo) {
        if (!state.loadingMore && layoutInfo.value.visibleItemsInfo.lastOrNull()?.index == characters.size - 1) {
            onLoadMore()
        }
    }

    LazyColumn(state = listState) {
        items(characters.size) {
            CharacterItem(
                characters[it],
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 6.dp)
                    .clickable { onItemClicked(characters[it].id) }
            ) { id, isFavorite ->
                onFavoriteCheckedChange(id, isFavorite)
            }
        }

        if (state.loadingMore) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            32.dp
                        ), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.Red, modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun CharacterItem(
    character: Character,
    modifier: Modifier = Modifier,
    onFavoriteCheckedChange: (Long, Boolean) -> Unit
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = rememberAsyncImagePainter(character.photo),
                contentDescription = "Character Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(225.dp)
                    .drawWithContent {
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Black.copy(alpha = 0.7f), Color.Transparent),
                                startY = 0f,
                                endY = size.height
                            ),
                            size = this.size
                        )
                    }
            )
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.TopStart)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = character.name,
                    fontSize = 28.sp,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(4f)
                )
                Box(
                    modifier = Modifier
                        .weight(1f),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Surface(
                        shape = CircleShape,
                        modifier = Modifier
                            .size(45.dp),
                        color = Color(0x77000000)
                    ) {
                        FavoriteButton(
                            modifier = Modifier.padding(8.dp),
                            characterFavoriteStatus = character.favorite
                        ) {
                            onFavoriteCheckedChange(character.id, it)
                        }
                    }
                }
            }
        }
    }
}

/*@Preview(heightDp = 175)
@Composable
fun CharacterItem_Preview() {
    CharacterItem(
        Character(1.toLong(), "ABYSS (AGE OF APOCALYPSE)", "Photo", false)
    )
}*/

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color(0xffE91E63),
    characterFavoriteStatus: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    var isFavorite by remember { mutableStateOf(characterFavoriteStatus) }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            isFavorite = !isFavorite
            onCheckedChange(it)
        }
    ) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.5f
                scaleY = 1.5f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = "Favorite button"
        )
    }
}

/*@Preview
@Composable
fun FavoriteButton_Preview() {
    FavoriteButton()
}*/
