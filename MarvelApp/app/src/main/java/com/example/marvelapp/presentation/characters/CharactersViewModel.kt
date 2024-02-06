package com.example.marvelapp.presentation.characters

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

    private val _favoriteCharacters: MutableStateFlow<List<Character>> = MutableStateFlow(emptyList())
    val favoriteCharacters: StateFlow<List<Character>> = _favoriteCharacters.asStateFlow()

    private val charactersList = mutableListOf<Character>()

    private val limit: Int = 20
    private var offset: Int = 0

    init {
        viewModelScope.launch {
            _viewState.value = CharactersViewState.Loading
            withContext(dispatcher) {
                val characters = repository.getCharactersWithCache()
                charactersList.addAll(characters)
                _viewState.value = CharactersViewState.Loaded(characters, false)
            }
        }
    }

    fun onLoadMore() {
        viewModelScope.launch {
            if (!(_viewState.value as CharactersViewState.Loaded).loadingMore) {
                offset += 20
                _viewState.value = CharactersViewState.Loaded(charactersList, true)
                withContext(dispatcher) {
                    Log.d("SALVA", "LLAMADA A LA API CON LIMIT: $limit y OFFSET: $offset")
                    val newCharacters = repository.getCharacters(limit, offset)
                    charactersList.addAll(newCharacters)
                    _viewState.value = CharactersViewState.Loaded(charactersList, false)
                }
            }
        }
    }
}