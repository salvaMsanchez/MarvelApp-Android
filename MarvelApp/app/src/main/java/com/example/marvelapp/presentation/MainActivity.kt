package com.example.marvelapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.marvelapp.presentation.characterDetail.CharacterDetailViewModel
import com.example.marvelapp.presentation.characters.CharactersViewModel
import com.example.marvelapp.presentation.navigation.MarvelAppNavigation
import com.example.marvelapp.ui.theme.MarvelAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val charactersViewModel: CharactersViewModel by viewModels()
    private val characterDetailViewModel: CharacterDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !mainViewModel.isReady.value
            }
        }
        setContent {
            MarvelAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    MarvelAppNavigation(
                        navController = navController,
                        charactersViewModel = charactersViewModel,
                        characterDetailViewModel = characterDetailViewModel,
                    )
                }
            }
        }
    }
}
