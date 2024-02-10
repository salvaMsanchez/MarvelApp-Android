package com.example.marvelapp.data.remote.response

import com.squareup.moshi.Json

data class ProductDataRemote(
    @Json(name = "data") val data: ProductData
)

data class ProductData(
    @Json(name = "results") val results: List<ProductRemote>
)

data class ProductRemote(
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "thumbnail") val thumbnail: Thumbnail,
)
