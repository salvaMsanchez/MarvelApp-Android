package com.example.marvelapp.data.remote

import com.example.marvelapp.data.remote.response.CharacterDataRemote
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") limit: String,
        @Query("offset") offset: String
    ): CharacterDataRemote
}