package com.example.marvelapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvelapp.data.local.models.CharacterLocal

@Database(entities = [CharacterLocal::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDAO(): CharacterDAO
}