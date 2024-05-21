package com.lucienguimaraes.datasource.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "joke_entity")
data class JokeEntity(
    @PrimaryKey val id: Long,
    val content: String,
    val favorite: Boolean = false,
)

fun jokeEntityNoFavorite() = JokeEntity(
    id = 5,
    content = "There are only 10 kinds of people in this world: those who know binary and those who don't.",
    favorite = false
)
