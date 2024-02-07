package com.example.marvelapp.presentation.navigation

sealed class Routes(val route: String) {
    object CharactersScreen : Routes("charactersScreen")
    object CharacterDetailScreen : Routes("characterDetailScreen/{characterId}") {
        fun createRoute(characterId: Long) = "characterDetailScreen/$characterId"
    }
}