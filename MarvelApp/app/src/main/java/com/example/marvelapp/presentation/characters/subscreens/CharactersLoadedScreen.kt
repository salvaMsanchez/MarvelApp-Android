package com.example.marvelapp.presentation.characters.subscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.marvelapp.domain.models.Character
import com.example.marvelapp.presentation.characters.CharactersViewState

@Composable
fun CharactersLoadedScreen(
    state: CharactersViewState.Loaded,
    onLoadMore: () -> Unit,
    onItemClicked: (Long) -> Unit
) {
    val listState = rememberLazyListState()

    val layoutInfo = remember { derivedStateOf { listState.layoutInfo } }

    LaunchedEffect(layoutInfo.value.visibleItemsInfo) {
        if (!state.loadingMore && layoutInfo.value.visibleItemsInfo.lastOrNull()?.index == state.data.size - 1) {
            onLoadMore()
        }
    }

    LazyColumn(state = listState) {
        items(state.data.size) {
            CharacterItem(
                state.data[it],
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 6.dp)
                    .clickable { onItemClicked(state.data[it].id) }
            )
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
    modifier: Modifier = Modifier
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
                            color = Color.Black.copy(alpha = 0.5f),
                            size = this.size
                        )
                    }
            )
            Text(
                text = character.name,
                fontSize = 28.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(40.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview(heightDp = 175)
@Composable
fun CharacterItem_Preview() {
    CharacterItem(
        Character(1.toLong(), "ABYSS (AGE OF APOCALYPSE)", "Photo", false)
    )
}