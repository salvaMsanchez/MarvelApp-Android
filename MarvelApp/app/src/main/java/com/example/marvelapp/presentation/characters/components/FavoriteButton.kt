package com.example.marvelapp.presentation.characters.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFFFFFFFF),
    characterFavoriteStatus: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    var isFavorite by remember { mutableStateOf(characterFavoriteStatus) }

    Surface(
        shape = CircleShape,
        modifier = Modifier
            .size(45.dp),
        color = Color(0x77000000)
    ) {
        IconToggleButton(
            checked = isFavorite,
            onCheckedChange = {
                isFavorite = !isFavorite
                onCheckedChange(it)
            }
        ) {
            Icon(
                tint = color,
                modifier = modifier.graphicsLayer {
                    scaleX = 1.5f
                    scaleY = 1.5f
                },
                imageVector = if (isFavorite) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
                contentDescription = "Favorite button"
            )
        }
    }
}

@Preview(backgroundColor = 0xFFED1D24, showBackground = true)
@Composable
fun FavoriteButton_Preview() {
    FavoriteButton(modifier = Modifier, color = Color(0xFFFFFFFF), characterFavoriteStatus = false, onCheckedChange = {})
}