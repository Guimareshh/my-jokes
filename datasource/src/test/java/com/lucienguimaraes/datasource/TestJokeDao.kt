package com.lucienguimaraes.datasource

import com.lucienguimaraes.datasource.dao.JokeDao
import com.lucienguimaraes.datasource.entities.JokeEntity

class ErrorTestJokeDao: JokeDao {

    override suspend fun insert(joke: JokeEntity) = throw Exception()

    override suspend fun getJokeById(jokeId: Long): JokeEntity = throw Exception()

    override suspend fun delete(joke: JokeEntity) = throw Exception()
}

class SuccessTestJokeDao: JokeDao {

    private val joke = JokeEntity(
        id = 23,
        content = "There are only 10 kinds of people in this world: those who know binary and those who don't.",
        favorite = true,
    )

    override suspend fun insert(joke: JokeEntity) = Unit

    override suspend fun getJokeById(jokeId: Long): JokeEntity = joke

    override suspend fun delete(joke: JokeEntity) = Unit
}

class EmptyTestJokeDao: JokeDao {

    override suspend fun insert(joke: JokeEntity) = Unit

    override suspend fun getJokeById(jokeId: Long): JokeEntity? = null

    override suspend fun delete(joke: JokeEntity) = Unit
}
