package com.example.marvelapp.presentation.characters.subscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.marvelapp.ui.theme.BackgroundColor
import com.example.marvelapp.ui.theme.MarvelColor

@Composable
fun CharactersLoadingScreen() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundColor), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = MarvelColor)
    }
}