package com.example.marvelapp.presentation.characters.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class FavoriteButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenPressedIcon_thenToggleState() {
        var isFavorite by mutableStateOf(false)

        composeTestRule.setContent {
            FavoriteButton(
                characterFavoriteStatus = isFavorite,
                onCheckedChange = { isFavorite = it })
        }

        composeTestRule.onNodeWithContentDescription("Favorite button").performClick()

        assert(isFavorite)
    }
}