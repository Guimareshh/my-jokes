package com.lucienguimaraes.joke

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JokeResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "joke") val content: List<String>?,
    @Json(name = "setup") val setup: String?,
    @Json(name = "delivery") val delivery: String?,
    @Json(name = "category") val category: JokeCategory,
    @Json(name = "type") val type: JokeType,
)

@JsonClass(generateAdapter = false)
enum class JokeCategory(val value: String) {
    ANY("Any"),
    CHRISTMAS("Christmas"),
    DARK("Dark"),
    MISC("Misc"),
    PROGRAMMING("Programming"),
    PUN("Pun"),
    SPOOKY("Spooky")
}

@JsonClass(generateAdapter = false)
enum class JokeType(val value: String) {
    SINGLE("single"),
    TWO_PART("twopart"),
}

fun jokeResponseMock() = JokeResponse(
    id = 23,
    content = listOf(
        "Why did the web developer walk out of a restaurant in disgust?",
        "The seating was laid out in tables.",
    ),
    category = JokeCategory.ANY,
    type = JokeType.SINGLE,
    delivery = null,
    setup = null,
)