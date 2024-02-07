package com.example.marvelapp.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelapp.data.local.models.CharacterLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDAO {
    @Query("SELECT * FROM characters")
    fun getCharacters(): List<CharacterLocal>

    @Query("SELECT * FROM characters")
    fun getCharactersWithFlow(): Flow<List<CharacterLocal>>

    @Query("SELECT * FROM characters WHERE favorite = 1")
    fun getFavoriteCharactersWithFlow(): Flow<List<CharacterLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<CharacterLocal>)

    @Query("UPDATE characters SET favorite = :isFavorite WHERE id = :characterId")
    fun updateFavoriteStatus(characterId: Long, isFavorite: Boolean)
}