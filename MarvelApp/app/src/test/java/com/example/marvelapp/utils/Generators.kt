package com.example.marvelapp.utils

import com.example.marvelapp.data.local.models.CharacterLocal
import com.example.marvelapp.data.remote.response.CharacterRemote
import com.example.marvelapp.data.remote.response.ProductRemote
import com.example.marvelapp.data.remote.response.Thumbnail
import com.example.marvelapp.domain.models.Product

fun generateLocalCharacters(): List<CharacterLocal> {
    return (0 until 40).map {
        CharacterLocal(
            it.toLong(),
            "NAME: $it",
            "DESCRIPTION: $it",
            "PHOTO: $it",
            false,
        )
    }
}

fun generateRemoteCharacters(): List<CharacterRemote> {
    return (0 until 10).map {
        CharacterRemote(
            it.toLong(),
            "NAME: $it",
            "DESCRIPTION: $it",
            Thumbnail("PATH", "EXTENSION")
        )
    }
}

fun generateRemoteProducts(): List<ProductRemote> {
    return (0 until 10).map {
        ProductRemote(
            it.toLong(),
            "TITLE: $it",
            Thumbnail("PATH", "EXTENSION"),
        )
    }
}

fun generateProducts(): List<Product> {
    return (0 until 10).map {
        Product(
            it.toLong(),
            "TITLE: $it",
            "PHOTO",
        )
    }
}
