package com.lucienguimaraes.joke

data class Joke(
    val id: Int,
    val content: List<String>,
    val category: JokeCategory,
    val type: JokeType,
)

enum class JokeCategory(val value: String) {
    ANY("Any"),
    CHRISTMAS("Christmas"),
    DARK("Dark"),
    MISC("Misc"),
    PROGRAMMING("Programming"),
    PUN("Pun"),
    SPOOKY("Spooky")
}

enum class JokeType(val value: String) {
    SINGLE("single"),
    TWO_PART("twopart"),
}

fun jokeMock() = Joke(
    id = 23,
    content = listOf("this is a joke"),
    category = JokeCategory.ANY,
    type = JokeType.SINGLE,
)