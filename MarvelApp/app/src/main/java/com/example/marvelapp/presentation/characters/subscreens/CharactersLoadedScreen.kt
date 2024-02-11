package com.example.marvelapp.presentation.characters.subscreens

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.marvelapp.presentation.characters.CharactersViewModel
import com.example.marvelapp.presentation.characters.CharactersViewState
import com.example.marvelapp.presentation.characters.components.CharacterItem
import com.example.marvelapp.presentation.characters.components.ExitDialog

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
    // OBSERVERS
    val characters by charactersViewModel.characters.collectAsState()

    // BACK HANDLER MANAGEMENT
    var showDialog by remember { mutableStateOf(false) }
    val activity = LocalContext.current as? Activity

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

    // LAZY COLUMN MANAGEMENT
    val listState = rememberLazyListState()
    val layoutInfo = remember { derivedStateOf { listState.layoutInfo } }

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
