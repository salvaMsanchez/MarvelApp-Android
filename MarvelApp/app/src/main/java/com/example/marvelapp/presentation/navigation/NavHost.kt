package com.example.marvelapp.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.marvelapp.R
import com.example.marvelapp.presentation.characterDetail.CharacterDetailScreen
import com.example.marvelapp.presentation.characterDetail.CharacterDetailViewModel
import com.example.marvelapp.presentation.characters.CharactersScreen
import com.example.marvelapp.presentation.characters.CharactersViewModel
import com.example.marvelapp.ui.theme.MarvelColor
import com.example.marvelapp.ui.theme.ToolbarColor
import com.example.marvelapp.ui.theme.WhiteColor

@OptIn(ExperimentalMaterial3Api::class)
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
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Image(
                                painter = painterResource(id = R.drawable.marvel_logo),
                                contentDescription = "",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.width(200.dp).height(50.dp)
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MarvelColor,
                            titleContentColor = WhiteColor
                        )
                    )
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    CharactersScreen(
                        charactersViewModel = charactersViewModel,
                        state = viewState,
                        onLoadMore = { charactersViewModel.onLoadMore() },
                        onItemClicked = { id ->
                            navController.navigate(Routes.CharacterDetailScreen.createRoute(id))
                        })
                }
            }
        }
        // CHARACTER DETAIL SCREEN
        composable(
            Routes.CharacterDetailScreen.route,
            arguments = listOf(navArgument("characterId") { type = NavType.LongType })
        ) { backStackEntry ->
            val viewState by characterDetailViewModel.viewState.collectAsState()
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text("Character Detail") },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = ToolbarColor,
                            titleContentColor = WhiteColor
                        ),
                        navigationIcon = {
                            IconButton(onClick = { navController.navigate(Routes.CharactersScreen.route) }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back Character Detail",
                                    tint = WhiteColor
                                )
                            }
                        }
                    )
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
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
    }
}
