package com.example.marvelapp.data.remote

import com.example.marvelapp.data.remote.response.CharacterDataRemote
import com.example.marvelapp.data.remote.response.ProductDataRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") limit: String,
        @Query("offset") offset: String
    ): CharacterDataRemote

    @GET("v1/public/characters/{characterId}/comics")
    suspend fun getComics(@Path("characterId") characterId: Long): ProductDataRemote

    @GET("v1/public/characters/{characterId}/series")
    suspend fun getSeries(@Path("characterId") characterId: Long): ProductDataRemote
}