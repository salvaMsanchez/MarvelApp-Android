package com.example.marvelapp.domain.models

data class Characters(
    val id: Long,
    val name: String,
    val photo: String,
    val favorite: Boolean,
)
