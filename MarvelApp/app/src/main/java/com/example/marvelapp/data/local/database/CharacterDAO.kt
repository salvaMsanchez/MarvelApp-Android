package com.example.marvelapp.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.marvelapp.data.local.models.CharacterLocal

@Dao
interface CharacterDAO {
    @Query("SELECT * FROM characters")
    fun getCharacters(): List<CharacterLocal>

    @Insert
    fun insertCharacters(characters: List<CharacterLocal>)
}