package com.example.marvelapp.data.local

import com.example.marvelapp.data.local.models.CharacterLocal
import com.example.marvelapp.data.remote.response.CharacterRemote

interface LocalDataSourceInterface {
    fun getCharacters(): List<CharacterLocal>
    fun insertCharacters(localCharacters: List<CharacterLocal>)
}