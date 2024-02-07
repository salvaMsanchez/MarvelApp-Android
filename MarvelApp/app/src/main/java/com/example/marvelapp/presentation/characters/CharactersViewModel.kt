package com.example.marvelapp.presentation.characters

import android.text.BoringLayout
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.domain.RepositoryInterface
import com.example.marvelapp.domain.models.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: RepositoryInterface,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    private val _viewState = MutableStateFlow<CharactersViewState>(CharactersViewState.Idle)
    val viewState: StateFlow<CharactersViewState> = _viewState.asStateFlow()

    private val _characters: MutableStateFlow<List<Character>> = MutableStateFlow(emptyList())
    val characters: StateFlow<List<Character>> = _characters.asStateFlow()

    private val _favoriteCharacters: MutableStateFlow<List<Character>> = MutableStateFlow(emptyList())
    val favoriteCharacters: StateFlow<List<Character>> = _favoriteCharacters.asStateFlow()

    private val limit: Int = 20
    private var offset: Int = 0

    init {
        getCharactersWithFlow()
        getFavoriteCharactersWithFlow()
        viewModelScope.launch {
            _viewState.value = CharactersViewState.Loading
            withContext(dispatcher) {
                repository.getCharactersWithCache()
                _viewState.value = CharactersViewState.Loaded(false)
            }
        }
    }

    private fun getCharactersWithFlow() {
        viewModelScope.launch(dispatcher) {
            repository.getCharactersWithFlow().collect { newCharacters ->
                _characters.update { newCharacters }
            }
        }
    }

    private fun getFavoriteCharactersWithFlow() {
        viewModelScope.launch(dispatcher) {
            repository.getFavoriteCharactersWithFlow().collect { favoriteCharacters ->
                _favoriteCharacters.update { favoriteCharacters }
            }
        }
    }

    fun onLoadMore() {
        viewModelScope.launch {
            if (!(_viewState.value as CharactersViewState.Loaded).loadingMore) {
                offset += 20
                _viewState.value = CharactersViewState.Loaded(true)
                withContext(dispatcher) {
                    repository.getCharacters(limit, offset)
                    _viewState.value = CharactersViewState.Loaded(false)
                }
            }
        }
    }

    fun onFavoriteClicked(characterId: Long, isFavorite: Boolean) {
        viewModelScope.launch {
            withContext(dispatcher) {
                repository.updateLocalFavoriteStatus(characterId, isFavorite)
            }
        }
    }
}