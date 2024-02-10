package com.example.marvelapp.presentation.characterDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marvelapp.R

@Composable
fun CharacterDetailScreen(characterId: Long) {
    LazyColumn(modifier = Modifier.background(Color.Black.copy(alpha = 0.85f))) {
        item {
            HeaderDetail("", "Hulk", modifier = Modifier.padding(bottom = 8.dp))
        }
        item {
            DescriptionDetail(
                "Caught in a gamma bomb explosion while trying to save the life of a teenager, Dr. Bruce Banner was transformed into the incredibly powerful creature called the Hulk. An all too often misunderstood hero, the angrier the Hulk gets, the stronger the Hulk gets.",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
        items(2) {
            ProductsDetail("COMICS", modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp))
            ProductsDetail("SERIES", modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp))
        }
    }
}

@Composable
fun ProductsDetail(sectionTitle: String, modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp), modifier = modifier) {
        Text(
            text = sectionTitle,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray,
            modifier = Modifier.padding(start = 8.dp)
        )
        LazyRow {
            items(2) {
                ProductItem(modifier = Modifier.padding(horizontal = 8.dp))
            }
        }
    }
}

@Composable
fun ProductItem(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .height(224.dp)
        .width(125.dp)) {
        Box(
            modifier = Modifier.height(180.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.marvel_comic),
                contentDescription = "Favorite Character Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Text(
            text = "Infinity Countdown (2018) #5",
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        )
    }
}

@Composable
fun DescriptionDetail(characterDescription: String, modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp), modifier = modifier) {
        Text(
            text = "DESCRIPTION",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )
        Text(
            text = characterDescription,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

@Composable
fun HeaderDetail(characterPhoto: String, characterName: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(300.dp)
            .fillMaxWidth()
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
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(200.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.marvel_comic),
                contentDescription = "Character Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = characterName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 16.dp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CharacterDetailScreen_Preview() {
    CharacterDetailScreen(1.toLong())
}