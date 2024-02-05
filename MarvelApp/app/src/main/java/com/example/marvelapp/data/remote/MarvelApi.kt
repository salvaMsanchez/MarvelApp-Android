package com.example.marvelapp.data.remote

import com.example.marvelapp.data.remote.response.CharactersDataRemote
import retrofit2.http.GET

interface MarvelApi {
    @GET("v1/public/characters")
    suspend fun getCharacters(): CharactersDataRemote
}