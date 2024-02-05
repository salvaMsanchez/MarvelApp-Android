package com.example.marvelapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.data.Repository
import com.example.marvelapp.domain.RepositoryInterface
import com.example.marvelapp.domain.models.Characters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: RepositoryInterface
) : ViewModel() {

    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val characters: List<Characters> = repository.getCharacters()
                Log.d("SALVA", "$characters")
                Log.d("SALVA", "TAMAÑO -> ${characters.size}")
            }
            _isReady.value = true
        }
    }
}