package com.example.marvelapp.presentation.characters

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CharactersScreen() {
    Text(text = "HOLA, MUNDO!")
}

@Preview(showSystemUi = true)
@Composable
fun CharactersScreen_Preview() {
    CharactersScreen()
}