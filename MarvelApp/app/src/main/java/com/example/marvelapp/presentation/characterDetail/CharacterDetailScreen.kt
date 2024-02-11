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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.marvelapp.R
import com.example.marvelapp.domain.models.Product
import com.example.marvelapp.presentation.characterDetail.components.ShimmerProductListItem
import com.example.marvelapp.presentation.characterDetail.components.shimmerEffect
import com.example.marvelapp.ui.theme.BackgroundColor
import com.example.marvelapp.ui.theme.MarvelColor

@Composable
fun CharacterDetailScreen(
    characterDetailViewModel: CharacterDetailViewModel,
    state: CharacterDetailViewState,
    onViewAppear: () -> Unit,
    onCharacterLoaded: () -> Unit
) {
    val comics by characterDetailViewModel.comics.collectAsState()
    val series by characterDetailViewModel.series.collectAsState()

    onViewAppear()

    when(state) {
        is CharacterDetailViewState.Idle -> {}
        is CharacterDetailViewState.Loading -> {
            CharacterDetailLoadingScreen()
        }
        is CharacterDetailViewState.CharacterLoaded -> {
            onCharacterLoaded()

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
    }
}

@Composable
fun CharacterDetailLoadingScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundColor), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = MarvelColor, modifier = Modifier)
    }
}

@Preview(showSystemUi = true)
@Composable
fun CharacterDetailLoadingScreen_Preview() {
    CharacterDetailLoadingScreen()
}

@Composable
fun ProductsDetail(
    sectionTitle: String,
    productsList: List<Product>,
    modifier: Modifier = Modifier
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = modifier) {
        Text(
            text = sectionTitle,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray,
            modifier = Modifier.padding(start = 8.dp)
        )
        LazyRow {
            items(if(productsList.isNotEmpty()) productsList.size else 3) {
                ShimmerProductListItem(
                    isLoading = productsList.isEmpty(),
                    contentAfterLoading = {
                        ProductItem(productsList[it], modifier = Modifier.padding(horizontal = 8.dp))
                    },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .height(232.dp)
            .width(125.dp)
    ) {
        Box(
            modifier = Modifier
                .height(180.dp)
                .shimmerEffect()
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.photo),
                contentDescription = "Favorite Character Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Text(
            text = product.title,
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(lineHeight = 15.sp),
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        )
    }
}

@Composable
fun DescriptionDetail(characterDescription: String, modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp), modifier = modifier) {
        Text(
            text = "DESCRIPTION",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )
        if (characterDescription.isNotEmpty()) {
            Text(
                text = characterDescription,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        } else {
            Text(
                text = "No description.",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}

@Composable
fun HeaderDetail(characterName: String, characterPhoto: String, modifier: Modifier = Modifier) {
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
                painter = rememberAsyncImagePainter(characterPhoto),
                contentDescription = "Character Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(180.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = characterName,
                fontSize = 22.sp,
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
