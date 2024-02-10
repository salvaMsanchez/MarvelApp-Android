package com.example.marvelapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.marvelapp.presentation.characterDetail.CharacterDetailScreen
import com.example.marvelapp.presentation.characterDetail.CharacterDetailViewModel
import com.example.marvelapp.presentation.characters.CharactersScreen
import com.example.marvelapp.presentation.characters.CharactersViewModel

@Composable
fun MarvelAppNavigation(
    navController: NavHostController,
    charactersViewModel: CharactersViewModel,
    characterDetailViewModel: CharacterDetailViewModel
) {
    NavHost(navController = navController, startDestination = Routes.CharactersScreen.route) {
        // CHARACTERS SCREEN
        composable(Routes.CharactersScreen.route) {
            val viewState by charactersViewModel.viewState.collectAsState()
            CharactersScreen(
                charactersViewModel = charactersViewModel,
                state = viewState,
                onLoadMore = { charactersViewModel.onLoadMore() },
                onItemClicked = { id ->
                    navController.navigate(Routes.CharacterDetailScreen.createRoute(id))
                })
        }
        // CHARACTER DETAIL SCREEN
        composable(
            Routes.CharacterDetailScreen.route,
            arguments = listOf(navArgument("characterId") { type = NavType.LongType })
        ) { backStackEntry ->
            val viewState by characterDetailViewModel.viewState.collectAsState()
            CharacterDetailScreen(
                characterDetailViewModel,
                viewState,
                onViewAppear = {
                    characterDetailViewModel.onViewAppear(
                        backStackEntry.arguments?.getLong("characterId") ?: 0.toLong()
                    )
                },
                onCharacterLoaded = {
                    characterDetailViewModel.onCharacterLoaded(
                        backStackEntry.arguments?.getLong("characterId") ?: 0.toLong()
                    )
                }
            )
        }
    }
}