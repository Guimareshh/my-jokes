package com.lucienguimaraes.datasource

import com.lucienguimaraes.datasource.network.responses.JokeResponse
import com.lucienguimaraes.datasource.network.responses.JokeType

internal fun jokeResponseSingle() = JokeResponse(
    id = 23,
    joke = "There are only 10 kinds of people in this world: those who know binary and those who don't.",
    type = JokeType.single,
    delivery = null,
    setup = null,
)

internal fun jokeResponseTwoPart() = JokeResponse(
    id = 5,
    joke = null,
    type = JokeType.twopart,
    setup = "Why did the web developer walk out of a restaurant in disgust?",
    delivery = "The seating was laid out in tables.",
)
