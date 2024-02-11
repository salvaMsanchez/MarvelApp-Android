package com.example.marvelapp.presentation.characters.subscreens

import android.app.Activity
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.example.marvelapp.domain.models.Character
import com.example.marvelapp.presentation.characterDetail.components.shimmerEffect
import com.example.marvelapp.presentation.characters.CharactersViewModel
import com.example.marvelapp.presentation.characters.CharactersViewState
import com.example.marvelapp.presentation.characters.components.FavoriteButton
import com.example.marvelapp.ui.theme.LogOutDialogColor

@Composable
fun CharactersLoadedScreen(
    charactersViewModel: CharactersViewModel,
    state: CharactersViewState.Loaded,
    favoriteCharactersScreen: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onLoadMore: () -> Unit,
    onItemClicked: (Long) -> Unit,
    onFavoriteCheckedChange: (Long, Boolean) -> Unit,
) {
    val activity = LocalContext.current as? Activity

    val characters by charactersViewModel.characters.collectAsState()

    val listState = rememberLazyListState()

    var showDialog by remember { mutableStateOf(false) }

    val layoutInfo = remember { derivedStateOf { listState.layoutInfo } }

    BackHandler(enabled = true) {
        showDialog = true
    }

    ExitDialog(
        show = showDialog,
        onDismiss = { showDialog = false },
    ) {
        activity?.finishAffinity()
        charactersViewModel.onConfirmExit()
    }

    LaunchedEffect(layoutInfo.value.visibleItemsInfo) {
        if (!state.loadingMore && layoutInfo.value.visibleItemsInfo.lastOrNull()?.index == characters.size - 1) {
            onLoadMore()
        }
    }

    LazyColumn(state = listState, modifier = modifier) {
        item {
            favoriteCharactersScreen()
        }
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
fun ExitDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    if (show) {
        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            ConstraintLayout(modifier = Modifier
                .fillMaxWidth()
                .height(175.dp)
                .background(LogOutDialogColor)) {
                val (title, subtitle, noButton, yesButton) = createRefs()

                Text("Salir de la aplicación.", fontSize = 20.sp, color = Color.White, modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                })
                Text("¿Estás seguro?", fontSize = 17.sp, color = Color.White, modifier = Modifier.constrainAs(subtitle) {
                    top.linkTo(title.bottom, margin = 6.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                })
                TextButton(onClick = { onDismiss() }, modifier = Modifier.constrainAs(noButton) {
                    end.linkTo(yesButton.start, margin = 6.dp)
                    bottom.linkTo(parent.bottom)
                }) {
                    Text(text = "NO", fontSize = 18.sp)
                }
                TextButton(onClick = { onConfirm() }, modifier = Modifier.constrainAs(yesButton) {
                    end.linkTo(parent.end, margin = 8.dp)
                    bottom.linkTo(parent.bottom)
                }) {
                    Text(text = "SÍ", fontSize = 18.sp)
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .shimmerEffect()
        ) {
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
