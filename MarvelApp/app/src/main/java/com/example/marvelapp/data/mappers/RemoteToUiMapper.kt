package com.example.marvelapp.data.mappers

import com.example.marvelapp.data.remote.response.ProductRemote
import com.example.marvelapp.domain.models.Product
import javax.inject.Inject

class RemoteToUiMapper @Inject constructor() {
    fun mapProduct(productsRemote: List<ProductRemote>): List<Product> = productsRemote.map {
        Product(
            it.id,
            it.title,
            "${it.thumbnail.path}.${it.thumbnail.extension}",
        )
    }
}