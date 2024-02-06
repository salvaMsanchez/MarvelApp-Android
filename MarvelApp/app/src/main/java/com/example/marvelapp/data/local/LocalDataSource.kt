package com.example.marvelapp.data.local

import com.example.marvelapp.data.local.database.CharacterDAO
import com.example.marvelapp.data.local.models.CharacterLocal
import com.example.marvelapp.data.remote.response.CharacterRemote
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: CharacterDAO
) : LocalDataSourceInterface {
    override fun getCharacters(): List<CharacterLocal> = dao.getCharacters()

    override fun insertCharacters(localCharacters: List<CharacterLocal>) = dao.insertCharacters(localCharacters)
}