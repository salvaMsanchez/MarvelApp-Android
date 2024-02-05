package com.example.marvelapp.data.remote.response

import com.squareup.moshi.Json

data class CharactersDataRemote(
    @Json(name = "data") val data: Data
)

data class Data(
    @Json(name = "results") val results: List<CharactersRemote>
)

data class CharactersRemote(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "thumbnail") val thumbnail: Thumbnail,
)

data class Thumbnail(
    @Json(name = "path") val path: String,
    @Json(name = "extension") val extension: String
)

//data class Extension(
//    @Json(name = "extension") val extension: String
//)
