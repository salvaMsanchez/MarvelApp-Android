package com.example.marvelapp.data.remote.response

import com.squareup.moshi.Json

data class ComicDataRemote(
    @Json(name = "data") val data: ComicData
)

data class ComicData(
    @Json(name = "results") val results: List<ComicRemote>
)

data class ComicRemote(
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "thumbnail") val thumbnail: Thumbnail,
)