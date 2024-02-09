package com.example.marvelapp.presentation.characterDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marvelapp.R

@Composable
fun CharacterDetailScreen(characterId: Long) {
    LazyColumn {
        item {
            HeaderDetail("", "Avengers")
        }
    }
}

@Composable
fun HeaderDetail(characterPhoto: String, characterName: String) {
    Box(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .background(Color.Red)
    ) {
        Image(
            painter = painterResource(id = R.drawable.marvel_comic),
            contentDescription = "Marvel Comic",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .drawWithContent {
                    drawContent()
                    drawRect(
                        color = Color.Black.copy(alpha = 0.7f),
                        size = this.size
                    )
                }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun CharacterDetailScreen_Preview() {
    CharacterDetailScreen(1.toLong())
}