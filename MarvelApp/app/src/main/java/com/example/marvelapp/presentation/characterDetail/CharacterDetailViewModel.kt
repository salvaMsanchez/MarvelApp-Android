package com.example.marvelapp.presentation.characterDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.domain.RepositoryInterface
import com.example.marvelapp.domain.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import com.example.marvelapp.domain.models.CharacterDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: RepositoryInterface,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {
    // PROPERTIES
    private val _viewState =
        MutableStateFlow<CharacterDetailViewState>(CharacterDetailViewState.Idle)
    val viewState: StateFlow<CharacterDetailViewState> = _viewState.asStateFlow()

    private val _comics: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())
    val comics: StateFlow<List<Product>> = _comics.asStateFlow()

    private val _series: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())
    val series: StateFlow<List<Product>> = _series.asStateFlow()

    // FUNCTIONS
    fun onViewAppear(characterId: Long) {
        viewModelScope.launch {
            withContext(dispatcher) {
                _viewState.value = CharacterDetailViewState.Loading
                val character: CharacterDetail = getCharacter(characterId)
                _viewState.value = CharacterDetailViewState.CharacterLoaded(character)
            }
        }
    }

    fun onCharacterLoaded(characterId: Long) {
        viewModelScope.launch {
            async(dispatcher) {
                getComics(characterId)
            }
            async(dispatcher) {
                getSeries(characterId)
            }
        }
    }

    private suspend fun getCharacter(characterId: Long): CharacterDetail =
        repository.getCharacter(characterId)

    private suspend fun getComics(characterId: Long) {
        _comics.value = repository.getComics(characterId)
    }

    private suspend fun getSeries(characterId: Long) {
        _series.value = repository.getSeries(characterId)
    }
}