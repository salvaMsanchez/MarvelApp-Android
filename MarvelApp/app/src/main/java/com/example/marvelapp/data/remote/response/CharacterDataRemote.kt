package com.example.marvelapp.data.remote.response

import com.squareup.moshi.Json

data class CharacterDataRemote(
    @Json(name = "data") val data: CharacterData
)

data class CharacterData(
    @Json(name = "results") val results: List<CharacterRemote>
)

data class CharacterRemote(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "thumbnail") val thumbnail: Thumbnail,
)

data class Thumbnail(
    @Json(name = "path") val path: String,
    @Json(name = "extension") val extension: String
)
