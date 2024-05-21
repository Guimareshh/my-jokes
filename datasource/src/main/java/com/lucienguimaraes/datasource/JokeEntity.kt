package com.lucienguimaraes.datasource

data class JokeEntity(
    val id: Long,
    val content: String,
    val favorite: Boolean = false,
)