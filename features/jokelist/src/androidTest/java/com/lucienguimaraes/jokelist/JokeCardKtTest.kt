package com.lucienguimaraes.jokelist

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.lucienguimaraes.datasource.entities.JokeEntity
import org.junit.Rule
import org.junit.Test

internal class JokeCardKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun jokeCard_isDisplayed() {
        // Given
        val joke = JokeEntity(1, "This is a joke", true)

        // When
        composeTestRule.setContent {
            JokeCard(joke = joke, onDeleteClick = {})
        }

        // Then
        composeTestRule.onNodeWithText(joke.content).assertIsDisplayed()
    }

    @Test
    fun jokeCard_deleteButton_isClickable() {
        // Given
        val joke = JokeEntity(1, "This is a joke", true)
        var deleteClicks = 0

        // When
        composeTestRule.setContent {
            JokeCard(joke = joke, onDeleteClick = { deleteClicks++ })
        }

        // Then
        composeTestRule.onNodeWithContentDescription("Delete").performClick()
        assert(deleteClicks == 1)
    }
}