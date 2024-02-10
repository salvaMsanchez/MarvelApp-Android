package com.example.marvelapp.presentation.characterDetail

import androidx.lifecycle.ViewModel
import com.example.marvelapp.data.Repository
import com.example.marvelapp.domain.RepositoryInterface
import com.example.marvelapp.presentation.characters.CharactersViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: RepositoryInterface,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
): ViewModel() {
    private val _viewState = MutableStateFlow<CharacterDetailViewState>(CharacterDetailViewState.Idle)
    val viewState: StateFlow<CharacterDetailViewState> = _viewState.asStateFlow()

    fun onViewAppear(characterId: Long) {

    }

    private fun getComics() {

    }
}