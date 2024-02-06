package com.example.marvelapp.domain.models

data class Character(
    val id: Long,
    val name: String,
    val photo: String,
    val favorite: Boolean,
)
